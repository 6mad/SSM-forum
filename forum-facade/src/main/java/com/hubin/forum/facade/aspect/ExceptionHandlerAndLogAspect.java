package com.hubin.forum.facade.aspect;

import com.hubin.forum.facade.support.ResultModelUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.exception.BizException;
import com.hubin.forum.common.support.LogUtil;
import com.hubin.forum.common.support.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Hubin
 * @create 2021/10/28
 * @desc
 **/
@Slf4j
@Component
@Aspect
public class ExceptionHandlerAndLogAspect {

    @Resource
    private HttpServletRequest httpServletRequest;

    @Around("execution(* com.hubin.forum.api..*.*(..))")
    public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
        Long start = System.currentTimeMillis();
        Object result;
        Throwable throwable = null;
        Boolean invokeSuccess = true;

        try {
            result = pjp.proceed();
        } catch (BizException bizException) {
            result = ResultModelUtil.fail(bizException.getCode(), bizException.getMessage());
            invokeSuccess = false;
        } catch (Exception e) {
            result = ResultModelUtil.fail(ErrorCodeEn.SYSTEM_ERROR);
            invokeSuccess = false;
            throwable = e;
        }

        logRequestInfo(invokeSuccess, pjp, start, result, throwable);

        return result;
    }

    private void logRequestInfo(Boolean invokeSuccess, ProceedingJoinPoint pjp, Long start, Object result, Throwable e) {
        Long cost = System.currentTimeMillis() - start;
        if (null == e) {
            LogUtil.info(log, "{}, ip={}. api={}#{}. cost={}ms. params={}. result={}"
                    , invokeSuccess ? "success" : "fail"
                    , httpServletRequest.getRemoteAddr()
                    , pjp.getSourceLocation().getWithinType().getName()
                    , pjp.getSignature().getName()
                    , cost
                    , StringUtil.toJSONString(pjp.getArgs())
                    , (null == result ? "" : StringUtil.toJSONString(result)));
            return;
        }

        LogUtil.info(log, e, "fail, ip={}. api={}#{}. cost={}ms. params={}. result={}. exception={}"
                , httpServletRequest.getRemoteAddr()
                , pjp.getSourceLocation().getWithinType().getName()
                , pjp.getSignature().getName()
                , cost
                , StringUtil.toJSONString(pjp.getArgs())
                , (null == result ? "" : StringUtil.toJSONString(result))
                , e.getClass().getName() + ": " + e.getMessage());
    }


}
