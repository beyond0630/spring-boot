package com.sweet.annotation;


import java.lang.annotation.*;

import com.sweet.datasource.DynamicDataSourceHolder;

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {

    int id() default DynamicDataSourceHolder.DEFAULT_DATASOURCE_ID;

}