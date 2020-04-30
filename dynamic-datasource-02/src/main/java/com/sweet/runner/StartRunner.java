package com.sweet.runner;

import java.util.List;

import com.sweet.datasource.DynamicDataSourceCache;
import com.sweet.datasource.DynamicDataSourceHolder;
import com.sweet.datasource.DynamicDataSourceScheduler;
import com.sweet.entities.DataSourceConfig;
import com.sweet.mapper.DataSourceConfigMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/29
 */
@Component
public class StartRunner implements ApplicationRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartRunner.class);

    private final DataSourceConfigMapper dataSourceConfigMapper;

    public StartRunner(final DataSourceConfigMapper dataSourceConfigMapper) {
        this.dataSourceConfigMapper = dataSourceConfigMapper;
    }

    @Override
    public void run(final ApplicationArguments args) throws Exception {
        LOGGER.debug("start runner run...");
        DynamicDataSourceHolder.setDefaultDataSource();

        List<DataSourceConfig> dataSourceConfigs = dataSourceConfigMapper.listDataSourceConfig();
        DynamicDataSourceCache.addDataSourceConfigs(dataSourceConfigs);

        DynamicDataSourceScheduler.clearDataSource();
    }
}
