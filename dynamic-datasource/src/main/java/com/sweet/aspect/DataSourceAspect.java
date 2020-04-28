package com.sweet.aspect;

import com.sweet.annotation.DataSource;
import com.sweet.datasource.DynamicDataSourceContextHolder;
import com.sweet.enums.DataSourceKey;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
@Aspect
@Order(-1)
@Component
public class DataSourceAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataSourceAspect.class);

    @Before("@annotation(dataSource))")
    public void switchDataSource(JoinPoint joinPoint, DataSource dataSource) {
        if (!DynamicDataSourceContextHolder.containDataSourceKey(dataSource.value().getName())) {
            LOGGER.error("DataSource [{}] doesn't exist, use default DataSource [{}]", dataSource.value(),
                    DataSourceKey.MASTER.getName());
        } else {
            DynamicDataSourceContextHolder.setDataSourceKey(dataSource.value().getName());
            LOGGER.debug("Switch DataSource to [{}] in Method [{}]", DynamicDataSourceContextHolder.getDataSourceKey(),
                    joinPoint.getSignature());
        }
    }

    @After("@annotation(dataSource)")
    public void restoreDataSource(JoinPoint joinPoint, DataSource dataSource) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        LOGGER.debug("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), joinPoint.getSignature());
    }
}
