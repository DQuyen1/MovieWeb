package com.example.movie.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @After("execution(* com.example.movie.controller..*(..))")
    public void logAfterAllMethod(JoinPoint joinPoint) {
      logger.info("Class name {} , method name {}", joinPoint.getTarget(), joinPoint.getSignature().getName());
    }




}
