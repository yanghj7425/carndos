package com.carndos.modules.demo.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {
    private static Logger log = LoggerFactory.getLogger(LogAspect.class);


    @Pointcut("@annotation( com.carndos.modules.demo.aop.annotation.Action)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        log.info("log is start: {}",args);
    }


    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        log.info("log is end");
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        log.info("around before");
        Object proceed = proceedingJoinPoint.proceed();
        log.info("around after {}", proceed);
        return proceed;
    }


}
