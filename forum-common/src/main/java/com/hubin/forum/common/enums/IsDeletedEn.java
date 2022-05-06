package com.hubin.forum.common.enums;

import lombok.Getter;

/**
 * @author Hubin
 * @create 22/2/1220/7/23
 * @desc
 **/
public enum IsDeletedEn {
    DELETED(1),
    NOT_DELETED(0),
    ;

    @Getter
    private Integer value;

    IsDeletedEn(Integer value) {
        this.value = value;
    }
}
