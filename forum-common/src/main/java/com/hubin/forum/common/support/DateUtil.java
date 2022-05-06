package com.hubin.forum.common.support;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Hubin
 * @create 2021/12/8
 * @desc
 **/
public class DateUtil {

    public static String toyyyyMMddHHmmss(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
