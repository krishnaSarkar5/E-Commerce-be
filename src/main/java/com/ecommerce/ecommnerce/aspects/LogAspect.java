package com.ecommerce.ecommnerce.aspects;


import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Order(0)
@Aspect
@Configuration
public class LogAspect {

    @Autowired
    private HttpServletRequest request;

    private static final Logger log = LoggerFactory.getLogger(LogAspect.class);

    @Around(value = "com.ecommerce.ecommnerce.aspects.AppPointCuts.mainPointCut()")
    public Object testAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("++ request url "+ request.getRequestURI());
        log.info("request url "+ request.getRequestURI());

        Object proceed = joinPoint.proceed();

        return proceed;
    }
}
