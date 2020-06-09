package com.beyond.dynamicdatasource.annotation;


import java.lang.annotation.*;

import com.beyond.dynamicdatasource.enums.DataSourceKey;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    /**
     * 切换数据源名称
     */
    DataSourceKey value() default DataSourceKey.MASTER;
}