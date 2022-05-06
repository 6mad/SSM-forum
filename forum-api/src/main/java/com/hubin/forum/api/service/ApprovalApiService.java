package com.hubin.forum.api.service;

import com.hubin.forum.api.model.ResultModel;

/**
 * @author Hubin
 * @create 2021/12/1
 * @desc
 **/
public interface ApprovalApiService {

    ResultModel<Long> create(Long postsId);

    ResultModel<Long> delete(Long postsId);

    ResultModel<Boolean> hasApproval(Long postsId);

}
