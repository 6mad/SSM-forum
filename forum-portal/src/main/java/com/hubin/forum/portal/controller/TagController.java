package com.hubin.forum.portal.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.response.tag.TagQueryResponse;
import com.hubin.forum.api.service.TagApiService;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.common.constant.Constant;
import com.hubin.forum.portal.request.BasePageRequest;
import com.hubin.forum.common.support.GlobalViewConfig;
import com.hubin.forum.portal.support.ViewException;
import com.hubin.forum.portal.support.WebConst;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hubin
 * @create 2021/11/19
 * @desc
 **/
@Slf4j
@Controller
@RequestMapping("/tag")
public class TagController {

    @Resource
    private TagApiService tagApiService;

    @Resource
    private WebUtil webUtil;

    @Resource
    private GlobalViewConfig globalViewConfig;

    @GetMapping("/{name}")
    public String index(@PathVariable("name") String name, BasePageRequest pageRequest, HttpServletRequest request, Model model) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebUtil.cookieGetSid(request));

        ResultModel<TagQueryResponse> resultModel = tagApiService.getByName(name);
        if (!resultModel.getSuccess()) {
            throw ViewException.build(resultModel.getMessage());
        }

        TagQueryResponse tagQueryResponse = resultModel.getData();
        model.addAttribute("tag", tag(tagQueryResponse));

        ResultModel<PageResponseModel<PostsVO>> pageResponseModelResultModel = tagPostsList(tagQueryResponse.getId(), pageRequest);
        if (pageResponseModelResultModel.getSuccess() && !ObjectUtils.isEmpty(pageResponseModelResultModel.getData())) {
            PageResponseModel<PostsVO> pageResponseModel = pageResponseModelResultModel.getData();

            model.addAttribute("postsList", webUtil.buildPostsList(pageResponseModel.getList()));
            model.addAttribute("pager", pager(pageRequest, pageResponseModel));
        } else {
            model.addAttribute("postsList", webUtil.buildArticles(new ArrayList<>()));

            PageResponseModel pageResponseModel = new PageResponseModel();
            pageResponseModel.setTotal(0L);
            model.addAttribute("pager", pager(pageRequest, pageResponseModel));
        }

        return "tag-info";
    }

    private Map<String, Object> tag(TagQueryResponse response) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", response.getId());
        map.put("name", response.getName());
        map.put("desc", response.getDescription());
        map.put("refCount", response.getRefCount());
        return map;
    }

    private ResultModel<PageResponseModel<PostsVO>> tagPostsList(Long tagId, BasePageRequest pageRequest) {
        PageRequestModel<Long> pageRequestModel = new PageRequestModel<>();
        pageRequestModel.setFilter(tagId);
        pageRequestModel.setPageSize(globalViewConfig.getPageSize());
        pageRequestModel.setPageNo(pageRequest.getPageNo());
        return tagApiService.pagePosts(pageRequestModel);
    }

    private Map<String, Object> pager(BasePageRequest pageRequest, PageResponseModel pageResponseModel) {
        String queryPath = "?" + WebConst.PAGE_NO_NAME + "=";
        return webUtil.buildPager(pageRequest.getPageNo(), queryPath, pageResponseModel);
    }
}
