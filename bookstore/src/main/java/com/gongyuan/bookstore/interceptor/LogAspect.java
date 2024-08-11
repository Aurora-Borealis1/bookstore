package com.gongyuan.bookstore.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: gongyuan
 * @date: 2024/8/11 12:38
 */
@Aspect
@Component
@Slf4j
public class LogAspect {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            " @annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void loggableMethods() {
    }

    @Around("loggableMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        String signature = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();
        log.info("Entering method: {}", signature);
        Object result = null;
        try {
            if (args != null && args.length > 0) {
                log.info("Method arguments: {}", Arrays.toString(args));
            }
            return result = joinPoint.proceed();
        } finally {
            log.info("[LogAspect#logAround] method: {}, time: {} ms, args: {}, result: {}", signature, System.currentTimeMillis() - start, Arrays.toString(args), result);
        }
    }

}
