package com.sweet.datasource;

import java.util.ArrayList;
import java.util.List;

import com.sweet.enums.DataSourceKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
public class DynamicDataSourceContextHolder {

    private static final Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceContextHolder.class);

    private static final ThreadLocal<Object> CONTEXT_HOLDER = ThreadLocal.withInitial(DataSourceKey.MASTER::getName);

    public static List<Object> dataSourceKeys = new ArrayList<>();

    public static void setDataSourceKey(String key) {
        CONTEXT_HOLDER.set(key);
    }

    public static Object getDataSourceKey() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceKey() {
        CONTEXT_HOLDER.remove();
    }

    public static Boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

}
