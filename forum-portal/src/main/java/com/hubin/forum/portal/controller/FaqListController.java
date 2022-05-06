package com.hubin.forum.portal.controller;

import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.faq.FaqUserPageRequest;
import com.hubin.forum.api.response.config.ConfigResponse;
import com.hubin.forum.api.response.faq.FaqHotsResponse;
import com.hubin.forum.api.response.faq.FaqUserPageResponse;
import com.hubin.forum.api.service.ConfigApiService;
import com.hubin.forum.api.service.FaqApiService;
import com.hubin.forum.common.enums.ConfigTypeEn;
import com.hubin.forum.common.support.SafesUtil;
import com.hubin.forum.portal.request.FaqRequest;
import com.hubin.forum.common.support.GlobalViewConfig;
import com.hubin.forum.portal.support.ViewException;
import com.hubin.forum.portal.support.WebConst;
import com.hubin.forum.portal.support.WebUtil;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author Hubin
 * @create 2021/10/25
 * @desc
 **/
@Slf4j
@Controller
@RequestMapping("/faq")
public class FaqListController {

    @Resource
    private FaqApiService faqApiService;

    @Resource
    private WebUtil webUtil;

    @Resource
    private GlobalViewConfig globalViewConfig;

    @Resource
    private ConfigApiService configApiService;

    private static final String ALL_TYPE_NAME = "全部问答";
    private static final String SOLVED_TYPE_NAME = "已解决";
    private static final String UNSOLVED_TYPE_NAME = "未解决";

    @GetMapping
    public String home(FaqRequest request, Model model) {
        request.setType(ObjectUtils.isEmpty(request.getType()) ? ALL_TYPE_NAME : request.getType());

        int solution;
        if (ALL_TYPE_NAME.equals(request.getType())) {
            solution = -1;
        } else if (SOLVED_TYPE_NAME.equals(request.getType())) {
            solution = 1;
        } else if (UNSOLVED_TYPE_NAME.equals(request.getType())) {
            solution = 0;
        } else {
            throw ViewException.build("问答类别不存在");
        }

        model.addAttribute("currentDomain", WebConst.DOMAIN_FAQ);
        model.addAttribute("typeList", typeList(request));
        model.addAttribute("hotFaqList", hotFaqList());
        model.addAttribute("usedTags", webUtil.usedTags());

        ResultModel<PageResponseModel<FaqUserPageResponse>> resultModel = userPage(request, solution);
        if (resultModel.getSuccess() && !ObjectUtils.isEmpty(resultModel.getData())) {
            PageResponseModel<FaqUserPageResponse> pageResponseModel = resultModel.getData();

            model.addAttribute("faqList", webUtil.buildFaqs(pageResponseModel.getList()));
            model.addAttribute("pager", pager(request, pageResponseModel));
        } else {
            model.addAttribute("faqList", webUtil.buildFaqs(new ArrayList<>()));

            PageResponseModel pageResponseModel = new PageResponseModel();
            pageResponseModel.setTotal(0L);
            model.addAttribute("pager", pager(request, pageResponseModel));
        }

        ResultModel<List<ConfigResponse>> configResult = configApiService.queryAvailable(Sets.newHashSet(ConfigTypeEn.SIDEBAR_CAROUSEL.getValue()));
        if (configResult.getSuccess() && !ObjectUtils.isEmpty(configResult.getData())) {
            model.addAttribute("sideCarouselList", webUtil.carouselList(configResult.getData(), ConfigTypeEn.SIDEBAR_CAROUSEL));
        } else {
            model.addAttribute("sideCarouselList", new ArrayList<>());
        }

        return "faq-list";
    }

    private List<Map<String, Object>> hotFaqList() {
        List<Map<String, Object>> postsList = new ArrayList<>();

        ResultModel<List<FaqHotsResponse>> resultModel = faqApiService.hots(10);
        if (!resultModel.getSuccess()) {
            return postsList;
        }

        SafesUtil.ofList(resultModel.getData()).forEach(faqHotsResponse -> {
            Map<String, Object> posts = new HashMap<>();
            posts.put("id", faqHotsResponse.getId());
            posts.put("title", faqHotsResponse.getTitle());
            posts.put("createdAt", faqHotsResponse.getCreateAt());
            postsList.add(posts);
        });

        return postsList;
    }

    private ResultModel<PageResponseModel<FaqUserPageResponse>> userPage(FaqRequest request, int solution) {
        PageRequestModel<FaqUserPageRequest> pageRequestModel = new PageRequestModel<>();
        pageRequestModel.setPageNo(request.getPageNo());
        pageRequestModel.setPageSize(globalViewConfig.getPageSize());
        pageRequestModel.setFilter(FaqUserPageRequest.builder()
                .solution(solution)
                .build());
        return faqApiService.userPage(pageRequestModel);
    }

    private Map<String, Object> pager(FaqRequest request, PageResponseModel pageResponseModel) {
        String queryPath = "?type=" + request.getType() + "&" + WebConst.PAGE_NO_NAME + "=";
        return webUtil.buildPager(request.getPageNo(), queryPath, pageResponseModel);
    }

    private List<Map<String, Object>> typeList(FaqRequest request) {
        List<Map<String, Object>> typeList = new ArrayList<>();
        Map<String, Object> all = new HashMap<>();
        all.put("name", ALL_TYPE_NAME);
        all.put("selected", request.getType().equals(ALL_TYPE_NAME));
        typeList.add(all);

        Map<String, Object> solved = new HashMap<>();
        solved.put("name", SOLVED_TYPE_NAME);
        solved.put("selected", request.getType().equals(SOLVED_TYPE_NAME));
        typeList.add(solved);


        Map<String, Object> unsolved = new HashMap<>();
        unsolved.put("name", UNSOLVED_TYPE_NAME);
        unsolved.put("selected", request.getType().equals(UNSOLVED_TYPE_NAME));
        typeList.add(unsolved);

        return typeList;
    }
}
