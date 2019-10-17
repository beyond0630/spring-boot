package com.sweet.aop;

import java.lang.reflect.Method;
import java.util.Objects;

import com.sweet.annotation.Lock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Aspect
@Component
public class ZooLock {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZooLock.class);

    private static final String LOCK_PREFIX = "/distributed-lock/";

    private final CuratorFramework zkClient;

    /**
     * 用于SpEL表达式解析.
     */
    private SpelExpressionParser parser = new SpelExpressionParser();
    /**
     * 用于获取方法参数定义名字.
     */
    private DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();

    public ZooLock(CuratorFramework zkClient) {this.zkClient = zkClient;}

    @Pointcut("@annotation(com.sweet.annotation.Lock)")
    public void pointcut() {

    }

    @Around("pointcut()")
    public Object doLock(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Lock zooLock = method.getAnnotation(Lock.class);
        if (StringUtils.isEmpty(zooLock.key())) {
            throw new IllegalArgumentException("分布式锁键不能为空");
        }
        String lockKey = LOCK_PREFIX + generateKeyBySpEL(zooLock.key(), point);
        InterProcessMutex lock = new InterProcessMutex(zkClient, lockKey);
        try {
            if (lock.acquire(zooLock.timeout(), zooLock.timeUnit())) {
                LOGGER.debug("acquire zookeeper lock success");
                return point.proceed();
            } else {
                throw new RuntimeException("请勿重复提交");
            }
        } finally {
            LOGGER.debug("release zookeeper lock");
            lock.release();
        }
    }

    private String generateKeyBySpEL(String spELString, ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] paramNames = nameDiscoverer.getParameterNames(methodSignature.getMethod());
        Expression expression = parser.parseExpression(spELString);
        EvaluationContext context = new StandardEvaluationContext();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            assert paramNames != null;
            context.setVariable(paramNames[i], args[i]);
        }
        return Objects.requireNonNull(expression.getValue(context)).toString();
    }
}
