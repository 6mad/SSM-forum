package com.hubin.forum.domain.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author Hubin
 * @create 22/2/12
 * @desc
 **/
public interface GithubService {

    JSONObject getUserInfo(String code);

}
