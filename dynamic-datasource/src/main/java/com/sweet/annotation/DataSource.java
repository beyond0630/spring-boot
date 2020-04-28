package com.sweet.annotation;


import java.lang.annotation.*;

import com.sweet.enums.DataSourceKey;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    /**
     * 切换数据源名称
     */
    DataSourceKey value() default DataSourceKey.MASTER;
}