package com.ecommerce.ecommnerce.aspects;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppPointCuts {

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerPointCut(){

    }

    @Pointcut("execution(* com.ecommerce.ecommnerce..*(..))")
    public void appPointCut(){

    }

    @Pointcut("appPointCut() && controllerPointCut()")
    public void mainPointCut(){

    }

}
