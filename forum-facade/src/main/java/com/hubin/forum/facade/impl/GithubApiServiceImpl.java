package com.hubin.forum.facade.impl;

import com.hubin.forum.facade.support.ResultModelUtil;
import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.github.GithubOauthLoginRequest;
import com.hubin.forum.api.service.GithubApiService;
import com.hubin.forum.app.manager.GithubManager;
import com.hubin.forum.common.support.CheckUtil;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 22/2/12
 * @desc
 **/
@Service
public class GithubApiServiceImpl implements GithubApiService {

    @Resource
    private GithubManager githubManager;

    @Override
    public ResultModel<String> oauthLogin(GithubOauthLoginRequest request) {
        CheckUtil.checkParamToast(request, "request");
        CheckUtil.checkParamToast(request.getCode(), "code");

        return ResultModelUtil.success(githubManager.oauthLogin(request));
    }
}
