package com.beyond.dynamicdatasource02.aspect;

import java.lang.reflect.Method;

import com.beyond.dynamicdatasource02.annotation.DataSource;
import com.beyond.dynamicdatasource02.datasource.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
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

    @Pointcut("execution(public * com.beyond.dynamicdatasource02.service.*.*(..))")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void switchDataSource(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        // 排除不可切换数据源的方法
        DataSource annotation = method.getAnnotation(DataSource.class);
        if (null == annotation) {
            DynamicDataSourceHolder.setDefaultDataSource();
        } else {
            DynamicDataSourceHolder.setCurrentDataSourceId(annotation.id());
        }
    }

    @After("pointCut()")
    public void restoreDataSource(JoinPoint joinPoint) {
        DynamicDataSourceHolder.setDefaultDataSource();
        LOGGER.debug("Restore default DataSource Method [{}]", joinPoint.getSignature());
    }
}
