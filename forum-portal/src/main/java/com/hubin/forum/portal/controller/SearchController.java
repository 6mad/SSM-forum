package com.hubin.forum.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.service.SearchApiService;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.portal.request.SearchRequest;
import com.hubin.forum.common.support.GlobalViewConfig;
import com.hubin.forum.portal.support.WebConst;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Controller
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchApiService searchApiService;

    @Resource
    private WebUtil webUtil;

    @Resource
    private GlobalViewConfig globalViewConfig;

    @GetMapping
    public String index(SearchRequest searchRequest, Model model) {
        model.addAttribute("key", searchRequest.getKey());

        ResultModel<PageResponseModel<PostsVO>> pageResponseModelResultModel = searchPostsList(searchRequest);
        if (pageResponseModelResultModel.getSuccess() && !ObjectUtils.isEmpty(pageResponseModelResultModel.getData())) {
            PageResponseModel<PostsVO> pageResponseModel = pageResponseModelResultModel.getData();

            model.addAttribute("postsList", webUtil.buildPostsList(pageResponseModel.getList()));
            model.addAttribute("pager", pager(searchRequest, pageResponseModel));
        } else {
            model.addAttribute("postsList", webUtil.buildArticles(new ArrayList<>()));

            PageResponseModel pageResponseModel = new PageResponseModel();
            pageResponseModel.setTotal(0L);
            model.addAttribute("pager", pager(searchRequest, pageResponseModel));
        }

        return "search";
    }

    private ResultModel<PageResponseModel<PostsVO>> searchPostsList(SearchRequest searchRequest) {
        PageRequestModel<String> pageRequestModel = new PageRequestModel<>();
        pageRequestModel.setFilter(searchRequest.getKey());
        pageRequestModel.setPageSize(globalViewConfig.getPageSize());
        pageRequestModel.setPageNo(searchRequest.getPageNo());

        return searchApiService.pagePostsSearch(pageRequestModel);
    }

    private Map<String, Object> pager(SearchRequest searchRequest, PageResponseModel pageResponseModel) {
        String queryPath = "?key=" + searchRequest.getKey() + "&" + WebConst.PAGE_NO_NAME + "=";
        return webUtil.buildPager(searchRequest.getPageNo(), queryPath, pageResponseModel);
    }

}
