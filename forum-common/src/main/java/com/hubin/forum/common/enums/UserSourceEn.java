package com.hubin.forum.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Hubin
 * @create 22/2/12
 * @desc
 **/
@Getter
@AllArgsConstructor
public enum UserSourceEn {
    /**
     *
     */
    REGISTER("REGISTER", "注册"),
    GITHUB("GITHUB", "github"),
    ;

    private String value;
    private String desc;

    public static UserSourceEn getEntity(String value) {
        for (UserSourceEn en : values()) {
            if (en.getValue().equalsIgnoreCase(value)) {
                return en;
            }
        }

        return null;
    }
}
