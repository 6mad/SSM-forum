package com.hubin.forum.portal.support;

import lombok.Data;

/**
 * @author Hubin
 * @create 2021/11/2
 * @desc
 **/
@Data
public class ViewException extends RuntimeException {

    private String message;

    public static ViewException build(String message) {
        ViewException viewException = new ViewException();
        viewException.setMessage(message);
        return viewException;
    }

}
