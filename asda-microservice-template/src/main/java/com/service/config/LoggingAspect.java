package com.service.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    @Value("${aspect.log.enable:true}")
    private boolean aspectLogEnable;

    /**
     * <h>logBeforeAndAfterServiceMethods</h>
     * Adds trace logging before a proceeding join point method call.
     * @param pjp The proceeding joint point
     * @return Result of method call
     * @throws Throwable
     */
    @Around("execution(* com.service..* (..))")
    public Object logBeforeAndAfterServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
        if (aspectLogEnable) {
            LOGGER.info("{} has started execution.", pjp.getSignature());
        }
        Object resultOfMethodCall = pjp.proceed();
        if (aspectLogEnable) {
            LOGGER.info("{} finished execution", pjp.getSignature());
        }
        return resultOfMethodCall;
    }
}
