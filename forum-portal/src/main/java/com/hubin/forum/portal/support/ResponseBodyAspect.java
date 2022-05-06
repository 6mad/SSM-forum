package com.hubin.forum.portal.support;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import com.hubin.forum.common.support.RequestContext;

/**
 * @author Hubin
 * @create 2021/11/25
 * @desc
 **/
@Component
@Aspect
public class ResponseBodyAspect {

    @Around("execution(* com.hubin.forum.portal..*.*(..)) && @annotation(postMapping)")
    public Object process(ProceedingJoinPoint joinPoint, PostMapping postMapping) throws Throwable {
        RequestContext.init();
        try {
            return joinPoint.proceed();
        } finally {
            RequestContext.removeAll();
        }
    }
}
