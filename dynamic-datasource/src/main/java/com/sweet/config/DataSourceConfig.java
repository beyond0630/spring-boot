package com.sweet.config;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.sweet.datasource.DynamicDataSource;
import com.sweet.datasource.DynamicDataSourceContextHolder;
import com.sweet.enums.DataSourceKey;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
@Configuration
public class DataSourceConfig {

    /**
     * 配置数据源
     */
    @Bean(name = "master")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置数据源
     */
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();

        Map<Object, Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(DataSourceKey.MASTER.getName(), master());
        dataSourceMap.put(DataSourceKey.SLAVE.getName(), slave());
        dynamicDataSource.setDefaultTargetDataSource(master());
        dynamicDataSource.setTargetDataSources(dataSourceMap);

        DynamicDataSourceContextHolder.dataSourceKeys.addAll(dataSourceMap.keySet());
        return dynamicDataSource;
    }

    @Bean
    @ConfigurationProperties("mybatis")
    public SqlSessionFactoryBean sqlSessionFactoryBean(
            @Qualifier(value = "dynamicDataSource") DataSource dynamicDataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        return sqlSessionFactoryBean;
    }

    /**
     * 事务
     */
    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier(value = "dynamicDataSource") DataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
