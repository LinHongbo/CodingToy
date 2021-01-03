package com.linhongbo.aspectJ;

import com.linhongbo.library.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAspectJ {

    @Pointcut("execution(@com.linhongbo.aspectJ.annotation.LogAround * *(..))")
    public void tagAround() {

    }

    @Around("tagAround()")
    public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger.d(joinPoint.getSignature().getName() + " in");
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            joinPoint.proceed(args);
        } else {
            joinPoint.proceed();
        }
        Logger.d(joinPoint.getSignature().getName() + " out");
    }
}
