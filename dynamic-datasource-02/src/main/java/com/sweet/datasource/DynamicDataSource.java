package com.sweet.datasource;

import java.sql.SQLException;
import java.util.Objects;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import com.sweet.entities.DataSourceConfig;
import com.sweet.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
public final class DynamicDataSource extends DruidDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSource.class);

    @Override
    public DruidPooledConnection getConnection() throws SQLException {
        Integer currentDataSourceId = DynamicDataSourceHolder.getCurrentDataSourceId();
        LOGGER.debug("use data source [id {}]... ", currentDataSourceId);
        DataSource dataSource = DynamicDataSourceCache.getDataSource(currentDataSourceId);
        if (Objects.isNull(dataSource)) {
            dataSource = intiDataSource(currentDataSourceId);
        }

        return (DruidPooledConnection) dataSource.getConnection();
    }

    private DataSource intiDataSource(final Integer currentDataSourceId) {
        LOGGER.debug("initialize data source [id {}]... ", currentDataSourceId);
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        if (currentDataSourceId == -1) {
            DataSourceProperties properties = SpringUtils.getBean(DataSourceProperties.class);
            druidDataSource.setUrl(properties.getUrl());
            druidDataSource.setUsername(properties.getUsername());
            druidDataSource.setPassword(properties.getPassword());
        } else {
            DataSourceConfig dataSourceConfig = DynamicDataSourceCache.getDataSourceConfig(currentDataSourceId);
            if (Objects.isNull(dataSourceConfig)) {
                throw new RuntimeException(String.format("Data source [id:%s] not exists ", currentDataSourceId));
            }
            druidDataSource.setUrl(dataSourceConfig.buildUrl());
            druidDataSource.setUsername(dataSourceConfig.getUsername());
            druidDataSource.setPassword(dataSourceConfig.getPassword());
        }
        DynamicDataSourceCache.addDataSource(currentDataSourceId, druidDataSource);
        return druidDataSource;
    }

    @Override
    public void close() {
        DynamicDataSourceCache.getAllDataSource()
                .forEach(DruidDataSource::close);
        LOGGER.debug("dynamic DataSource closed");
    }
}
