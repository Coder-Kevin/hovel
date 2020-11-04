package com.hovel.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kevin
 */
@Aspect
@Configuration
public class TestAop {

    private Logger logger = LoggerFactory.getLogger(TestAop.class);

    @Pointcut("execution (* com.hovel.spring.sevice.impl.*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        logger.info("方法调用开始:{}", proceedingJoinPoint.getSignature().getName());
        logger.info("方法参数:{}", proceedingJoinPoint.getArgs());
        logger.info("StaticPart:{}", proceedingJoinPoint.getStaticPart());
        Object o = proceedingJoinPoint.proceed();
        logger.info("方法调用返回结果:{}", o);
        return o;
    }
}
