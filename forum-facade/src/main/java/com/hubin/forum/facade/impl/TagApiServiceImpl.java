package com.hubin.forum.facade.impl;

import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.ArticleValidator;
import com.hubin.forum.facade.validator.PageRequestModelValidator;
import com.hubin.forum.facade.validator.TagValidator;
import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.tag.TagCreateRequest;
import com.hubin.forum.api.request.tag.TagPageRequest;
import com.hubin.forum.api.response.tag.TagPageResponse;
import com.hubin.forum.api.response.tag.TagQueryResponse;
import com.hubin.forum.api.service.TagApiService;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.app.manager.TagManager;
import com.hubin.forum.common.support.CheckUtil;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
@Service
public class TagApiServiceImpl implements TagApiService {

    @Resource
    private TagManager tagManager;

    @Override
    public ResultModel<List<TagQueryResponse>> queryAllRef() {
        return ResultModelUtil.success(tagManager.queryAllRef());
    }

    @Override
    public ResultModel create(TagCreateRequest request) {
        TagValidator.create(request);

        tagManager.create(request);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<TagQueryResponse> getByName(String name) {
        return ResultModelUtil.success(tagManager.getByName(name));
    }

    @Override
    public ResultModel<List<TagQueryResponse>> queryAll() {
        return ResultModelUtil.success(tagManager.queryAll());
    }

    @Override
    public ResultModel<List<TagQueryResponse>> queryInIds(Set<Long> ids) {
        CheckUtil.checkParamToast(ids, "ids");

        return ResultModelUtil.success(tagManager.queryInIds(ids));
    }

    @Override
    public ResultModel<PageResponseModel<PostsVO>> pagePosts(PageRequestModel<Long> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(tagManager.pagePosts(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<PostsVO>> pagePostsByTagIds(PageRequestModel<Set<Long>> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(tagManager.pagePostsByTagIds(pageRequestModel));
    }

    @Override
    public ResultModel<PageResponseModel<TagPageResponse>> page(PageRequestModel<TagPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(tagManager.page(pageRequestModel));
    }

    @Override
    public ResultModel auditState(AdminBooleanRequest booleanRequest) {
        ArticleValidator.validatorBooleanRequest(booleanRequest);

        tagManager.tagAuditState(booleanRequest);

        return ResultModelUtil.success();
    }
}
