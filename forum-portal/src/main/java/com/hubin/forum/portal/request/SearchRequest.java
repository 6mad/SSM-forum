package com.hubin.forum.portal.request;

import lombok.Data;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Data
public class SearchRequest extends BasePageRequest {

    private String key;
}
