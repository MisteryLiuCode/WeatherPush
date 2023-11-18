package com.liu.weathermail.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author: houcheng
 * @date: 2021/7/24 11:11
 * @version: V1.0
 * @description:
 * @modify:
 */
@Slf4j
@Aspect
@Component
public class LoggerAspect {

    /**
     * 以注解作为切点切入
     */
    @Pointcut("@annotation(com.liu.weathermail.annotation.PrintLog)")
    public void logPoint() {

    }


    /**
     * 前置通知，打印一下东西
     */
    @Before("logPoint()")
    public void beforeInvokeMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("当前时间：" + LocalDateTime.now());
        log.info("开始执行方法：" + signature.getDeclaringTypeName() + "-" + signature.getName());
    }

    @After("logPoint()")
    public void afterInvokeMethod(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("当前时间：" + LocalDateTime.now());
        log.info("方法执行完毕：" + signature.getDeclaringTypeName() + "-" + signature.getName());
    }

}
