package com.beyond.dynamicdatasource02.datasource;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSource;
import com.beyond.dynamicdatasource02.entities.DataSourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/29
 */
public final class DynamicDataSourceCache {

    public static final long CLEAR_DATA_SOURCE_TIME = 5 * 60 * 1000;

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceCache.class);

    private DynamicDataSourceCache() {
        throw new UnsupportedOperationException();
    }

    private static final Map<Integer, DataSourceConfig> DATA_SOURCE_CONFIG = new ConcurrentHashMap<>();

    private static final Map<Integer, DynamicDataSourceManager> DATA_SOURCES = new ConcurrentHashMap<>();

    public static void addDataSourceConfigs(List<DataSourceConfig> dataSourceConfigs) {
        dataSourceConfigs.forEach(DynamicDataSourceCache::addDataSourceConfig);
    }

    public static void addDataSourceConfig(DataSourceConfig dataSourceConfig) {
        DATA_SOURCE_CONFIG.put(dataSourceConfig.getId(), dataSourceConfig);
    }

    public static DataSourceConfig getDataSourceConfig(Integer id) {
        return DATA_SOURCE_CONFIG.get(id);
    }

    public static DataSourceConfig removeDataSourceConfig(Integer id) {
        return DATA_SOURCE_CONFIG.remove(id);
    }


    public static void addDataSource(Integer id, DruidDataSource dataSource) {
        DATA_SOURCES.put(id, new DynamicDataSourceManager(dataSource));
    }

    public static DataSource getDataSource(Integer id) {
        DynamicDataSourceManager dynamicDataSourceManager = DATA_SOURCES.get(id);
        if (Objects.isNull(dynamicDataSourceManager)) {
            return null;
        }
        dynamicDataSourceManager.refreshTime();
        return dynamicDataSourceManager.getDataSource();
    }

    public static List<DruidDataSource> getAllDataSource() {
        return DATA_SOURCES.values()
                .stream()
                .map(DynamicDataSourceManager::getDataSource)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static void removeDataSource(Integer id) {
        DATA_SOURCES.remove(id);
    }

    /**
     * 清除超时的数据源
     */
    public static synchronized void clearExpiredDatasource() {
        LOGGER.debug("executor clear data source");
        DATA_SOURCES.forEach((k, v) -> {
            // 排除默认数据源
            if (DynamicDataSourceHolder.DEFAULT_DATASOURCE_ID != k) {
                if (v.isExpired()) {
                    DATA_SOURCES.remove(k);
                }
            }
        });
    }

}
