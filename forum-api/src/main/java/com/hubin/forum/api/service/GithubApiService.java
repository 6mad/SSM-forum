package com.hubin.forum.api.service;

import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.github.GithubOauthLoginRequest;

/**
 * @author Hubin
 * @create 22/2/12
 * @desc
 **/
public interface GithubApiService {

    ResultModel<String> oauthLogin(GithubOauthLoginRequest request);

}
