package com.hubin.forum.facade.impl;

import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.config.ConfigAddRequest;
import com.hubin.forum.api.request.config.ConfigPageRequest;
import com.hubin.forum.api.request.config.ConfigUpdateRequest;
import com.hubin.forum.api.response.config.ConfigResponse;
import com.hubin.forum.api.service.ConfigApiService;
import com.hubin.forum.app.manager.ConfigManager;
import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.PageRequestModelValidator;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
@Service
public class ConfigApiServiceImpl implements ConfigApiService {

    @Resource
    private ConfigManager configManager;

    @Override
    public ResultModel add(ConfigAddRequest request) {

        configManager.add(request);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel update(ConfigUpdateRequest request) {

        configManager.update(request);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel state(AdminBooleanRequest request) {

        configManager.state(request);

        return ResultModelUtil.success();
    }

    @Override
    public ResultModel<PageResponseModel<ConfigResponse>> page(PageRequestModel<ConfigPageRequest> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(configManager.page(pageRequestModel));
    }

    @Override
    public ResultModel<List<ConfigResponse>> queryAvailable(Set<String> types) {

        return ResultModelUtil.success(configManager.queryAvailable(types));
    }
}
