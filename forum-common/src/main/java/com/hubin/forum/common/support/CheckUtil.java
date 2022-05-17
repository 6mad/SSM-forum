package com.hubin.forum.common.support;

import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.exception.BizException;
import org.springframework.util.ObjectUtils;

import java.text.MessageFormat;

/**
 * @author Hubin
 * @create 22/2/12
 * @desc
 **/
public class CheckUtil {

    private CheckUtil() {
    }

    /**
     * 检查请求路径参数是否为空
     *
     * @param o
     */
    public static void checkParamToast(Object o, String message) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(ErrorCodeEn.PARAM_CHECK_ERROR.getCode(),
                    MessageFormat.format(ErrorCodeEn.PARAM_CHECK_ERROR.getMessage(), message));
        }
    }

    /**
     * @param o         被检查的对象
     * @param errorCode 抛出的异常信息
     * @desc 检查是否为空，为空则报异常
     */
    public static void isEmpty(Object o, ErrorCodeEn errorCode) {
        if (ObjectUtils.isEmpty(o)) {
            throw new BizException(errorCode);
        }
    }

    public static void isNotEmpty(Object o, ErrorCodeEn errorCode) {
        if (!ObjectUtils.isEmpty(o)) {
            throw new BizException(errorCode);
        }
    }

    public static void isFalse(Boolean b, ErrorCodeEn errorCode) {
        if (!b) {
            throw new BizException(errorCode);
        }
    }

    public static void isTrue(Boolean b, ErrorCodeEn errorCode) {
        if (b) {
            throw new BizException(errorCode);
        }
    }

}