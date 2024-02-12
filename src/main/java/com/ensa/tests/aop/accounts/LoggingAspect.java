package com.ensa.tests.aop.accounts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@EnableAspectJAutoProxy
@Aspect
public class LoggingAspect {
    @Before("execution(void com.ensa.tests.DevOpsCycleApplication.run())")
    public void beforeMainCodeAdvice(){
        System.out.println("--------- Before running the main method ---------");
    }
}
