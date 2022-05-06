package com.hubin.forum.api.service;

import com.hubin.forum.api.response.config.ConfigResponse;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.config.ConfigAddRequest;
import com.hubin.forum.api.request.config.ConfigPageRequest;
import com.hubin.forum.api.request.config.ConfigUpdateRequest;

import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
public interface ConfigApiService {

    /**
     * 添加
     * @param request
     * @return
     */
    ResultModel add(ConfigAddRequest request);

    /**
     * 更新
     * @param request
     * @return
     */
    ResultModel update(ConfigUpdateRequest request);

    /**
     * 更新状态
     * @param request
     * @return
     */
    ResultModel state(AdminBooleanRequest request);

    /**
     * 分页查询
     * @param pageRequestModel
     * @return
     */
    ResultModel<PageResponseModel<ConfigResponse>> page(PageRequestModel<ConfigPageRequest> pageRequestModel);

    /**
     * 类别查询可用
     * @param types
     * @return
     */
    ResultModel<List<ConfigResponse>> queryAvailable(Set<String> types);
}
