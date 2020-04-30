package com.sweet.datasource;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
public final class DynamicDataSourceHolder {

    public static final int DEFAULT_DATASOURCE_ID = -1;

    private static final ThreadLocal<Integer> THREAD_LOCAL = ThreadLocal.withInitial(() -> DEFAULT_DATASOURCE_ID);

    public static void setDefaultDataSource() {
        THREAD_LOCAL.remove();
        setCurrentDataSourceId(DEFAULT_DATASOURCE_ID);
    }

    public static void setCurrentDataSourceId(final Integer id) {
        THREAD_LOCAL.set(id);
    }

    public static Integer getCurrentDataSourceId() {
        return THREAD_LOCAL.get();
    }

}
