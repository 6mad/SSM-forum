package com.hubin.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Hubin
 * @create 2021/10/22
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum MessageContentTypeEn {
    HTML("HTML", "html"),
    TEXT("TEXT", "text"),
    ;

    private String value;
    private String desc;

    public static MessageContentTypeEn getEntity(String value) {
        for (MessageContentTypeEn contentType : values()) {
            if (contentType.getValue().equals(value)) {
                return contentType;
            }
        }
        return null;
    }
}
