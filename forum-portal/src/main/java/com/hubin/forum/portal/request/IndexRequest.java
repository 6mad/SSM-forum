package com.hubin.forum.portal.request;

import lombok.Data;

/**
 * @author Hubin
 * @create 2021/10/24
 * @desc
 **/
@Data
public class IndexRequest extends BasePageRequest {

    private String type;

    private String toast;

    private String token;
}
