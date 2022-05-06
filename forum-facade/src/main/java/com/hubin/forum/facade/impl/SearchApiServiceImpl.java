package com.hubin.forum.facade.impl;

import com.hubin.forum.facade.support.ResultModelUtil;
import com.hubin.forum.facade.validator.PageRequestModelValidator;
import org.springframework.stereotype.Service;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.service.SearchApiService;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.app.manager.SearchManager;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Service
public class SearchApiServiceImpl implements SearchApiService {

    @Resource
    private SearchManager searchManager;

    @Override
    public ResultModel<PageResponseModel<PostsVO>> pagePostsSearch(PageRequestModel<String> pageRequestModel) {
        PageRequestModelValidator.validator(pageRequestModel);

        return ResultModelUtil.success(searchManager.pagePostsSearch(pageRequestModel));
    }

}
