package com.hubin.forum.facade.validator;

import com.hubin.forum.api.request.message.MessagePageRequest;
import com.hubin.forum.common.enums.MessageTypeEn;
import com.hubin.forum.common.support.CheckUtil;

/**
 * @author Hubin
 * @create 2021/12/5
 * @desc
 **/
public class MessageValidator {

    public static void page(MessagePageRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getTypeDesc(), "typeDesc");
        CheckUtil.checkParamToast(MessageTypeEn.getEntityByDesc(request.getTypeDesc()), "不存在的消息类型");
    }
}
