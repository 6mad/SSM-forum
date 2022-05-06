package com.hubin.forum.facade.impl;

import com.hubin.forum.facade.support.ResultModelUtil;
import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.service.ApprovalApiService;
import com.hubin.forum.app.manager.ApprovalManager;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/1
 * @desc
 **/
@Service
public class ApprovalApiServiceImpl implements ApprovalApiService {

    @Resource
    private ApprovalManager approvalManager;

    @Override
    public ResultModel<Long> create(Long postsId) {
        return ResultModelUtil.success(approvalManager.create(postsId));
    }

    @Override
    public ResultModel<Long> delete(Long postsId) {
        return ResultModelUtil.success(approvalManager.delete(postsId));
    }

    @Override
    public ResultModel<Boolean> hasApproval(Long postsId) {
        return ResultModelUtil.success(approvalManager.hasApproval(postsId));
    }

}
