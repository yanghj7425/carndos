package com.carndos.modules.demo.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation( com.carndos.modules.demo.aop.annotation.Action)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {

        Object[] args = joinPoint.getArgs();
        List<Object> temp = Arrays.asList(args);
        List<List<Integer>> lists = Optional.of(temp)
                .map(v -> v.stream()
                        .map(list -> Arrays.asList(list).stream()
                                .map(i -> {
                                    System.out.println(i.toString());
                                    return Integer.parseInt(i.toString()) * 3;
                                })
                                .collect(Collectors.toList())).collect(Collectors.toList()))
                .orElse(new ArrayList<>());


        lists.forEach(k -> k.forEach(System.out::println));


        System.out.println("log is start");
    }


    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        System.out.println("log is end ");
    }

    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("around before");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("around after \t" + proceed);
        return proceed;
    }


}
