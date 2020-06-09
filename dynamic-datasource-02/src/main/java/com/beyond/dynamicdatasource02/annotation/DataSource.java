package com.beyond.dynamicdatasource02.annotation;


import java.lang.annotation.*;

import com.beyond.dynamicdatasource02.datasource.DynamicDataSourceHolder;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    int id() default DynamicDataSourceHolder.DEFAULT_DATASOURCE_ID;

}