package com.hubin.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Hubin
 * @create 2021/9/8
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum UserSexEn {
    /**
     *
     */
    UNKNOWN("UNKNOWN", "未知"),
    MAN("MAN", "男"),
    WOMAN("WOMAN", "女"),
    ;

    private String value;
    private String desc;

    public static UserSexEn getEntity(String value) {
        for (UserSexEn userSexEn : values()) {
            if (userSexEn.getValue().equalsIgnoreCase(value)) {
                return userSexEn;
            }
        }

        return null;
    }
}
