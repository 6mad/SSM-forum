package com.hubin.forum.api.service;

import com.hubin.forum.api.response.tag.TagPageResponse;
import com.hubin.forum.api.response.tag.TagQueryResponse;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.tag.TagCreateRequest;
import com.hubin.forum.api.request.tag.TagPageRequest;
import com.hubin.forum.api.vo.PostsVO;

import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 22/2/18
 * @desc
 **/
public interface TagApiService {

    ResultModel<List<TagQueryResponse>> queryAllRef();

    ResultModel create(TagCreateRequest request);

    ResultModel<TagQueryResponse> getByName(String name);

    ResultModel<List<TagQueryResponse>> queryAll();

    ResultModel<List<TagQueryResponse>> queryInIds(Set<Long> ids);

    ResultModel<PageResponseModel<PostsVO>> pagePosts(PageRequestModel<Long> pageRequestModel);

    ResultModel<PageResponseModel<PostsVO>> pagePostsByTagIds(PageRequestModel<Set<Long>> pageRequestModel);

    ResultModel<PageResponseModel<TagPageResponse>> page(PageRequestModel<TagPageRequest> pageRequestModel);

    ResultModel auditState(AdminBooleanRequest booleanRequest);
}
