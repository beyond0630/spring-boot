package com.sweet.datasource;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.sweet.datasource.DynamicDataSourceCache.CLEAR_DATA_SOURCE_TIME;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/29
 */
public final class DynamicDataSourceScheduler {

    private static final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);

    public static void clearDataSource() {
        executor.scheduleAtFixedRate(DynamicDataSourceCache::clearExpiredDatasource, CLEAR_DATA_SOURCE_TIME,
                CLEAR_DATA_SOURCE_TIME, TimeUnit.MILLISECONDS);
    }

}
