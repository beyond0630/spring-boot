package org.beyond.nacos.config.config;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Beyond
 */
@Configuration
@RefreshScope
public class DataBaseConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseConfiguration.class);

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean(name="datasource")
    @RefreshScope
    public DataSource dataSource()
    {
        HikariDataSource datasource = new HikariDataSource();
        LOGGER.info(url);
        datasource.setJdbcUrl(this.url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        return datasource;
    }
}
