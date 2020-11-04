package com.hovel.spring.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

/**
 * @author Kevin
 */
@Aspect
@Configuration
@Slf4j
public class LogAop {

    @Pointcut("execution (* com.hovel.spring.sevice.impl.*.*(..))")
    public void webLog() {
    }

    @Around("webLog()")
    public Object aroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("方法调用开始:{}", proceedingJoinPoint.getSignature().getName());
        log.info("方法参数:{}", proceedingJoinPoint.getArgs());
        log.info("StaticPart:{}", proceedingJoinPoint.getStaticPart());
        Object o = proceedingJoinPoint.proceed();
        log.info("方法调用返回结果:{}", o);
        return o;
    }
}
