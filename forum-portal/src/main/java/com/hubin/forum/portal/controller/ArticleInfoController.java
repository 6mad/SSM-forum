package com.hubin.forum.portal.controller;

import com.hubin.forum.portal.support.ViewException;
import com.hubin.forum.portal.support.WebConst;
import com.hubin.forum.portal.support.WebContext;
import com.hubin.forum.portal.support.WebUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.request.article.ArticleAuthorPageRequest;
import com.hubin.forum.api.response.article.ArticleInfoResponse;
import com.hubin.forum.api.response.article.ArticleUserPageResponse;
import com.hubin.forum.api.response.comment.CommentPageResponse;
import com.hubin.forum.api.service.ApprovalApiService;
import com.hubin.forum.api.service.ArticleApiService;
import com.hubin.forum.api.service.CommentApiService;
import com.hubin.forum.api.service.UserApiService;
import com.hubin.forum.api.vo.TagVO;
import com.hubin.forum.common.constant.Constant;
import com.hubin.forum.common.support.GlobalViewConfig;
import com.hubin.forum.common.support.SafesUtil;
import com.hubin.forum.portal.request.BasePageRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 22/2/1220/10/26
 * @desc
 **/
@Controller
@RequestMapping("/article")
public class ArticleInfoController {

    @Resource
    private ArticleApiService articleApiService;

    @Resource
    private CommentApiService commentApiService;

    @Resource
    private UserApiService userApiService;

    @Resource
    private ApprovalApiService approvalApiService;

    @Resource
    private WebUtil webUtil;

    @Resource
    private GlobalViewConfig globalViewConfig;

    @GetMapping("/{id}")
    public String home(@PathVariable("id") Long id, BasePageRequest pageRequest, HttpServletRequest request, Model model) {
        request.setAttribute(Constant.REQUEST_HEADER_TOKEN_KEY, WebContext.getCurrentSid());

        ResultModel<ArticleInfoResponse> resultModel = articleApiService.info(id);
        if (!resultModel.getSuccess() || ObjectUtils.isEmpty(resultModel)) {
            throw ViewException.build(resultModel.getMessage());
        }
        ArticleInfoResponse articleInfoResponse = resultModel.getData();

        model.addAttribute("posts", webUtil.buildPostsInfo(articleInfoResponse));
        model.addAttribute("hasFollow", hasFollow(articleInfoResponse.getAuthorId()));
        model.addAttribute("hasApproval", hasApproval(id));
        model.addAttribute("authArticleList", authArticleList(articleInfoResponse.getAuthorId()));

        PageResponseModel pageResponseModel = pickComment(model, id, pageRequest);
        model.addAttribute("pager", pager(pageRequest.getPageNo(), pageResponseModel));

        Set<Long> tagIds = SafesUtil.ofList(articleInfoResponse.getTags()).stream().map(TagVO::getId).collect(Collectors.toSet());
        model.addAttribute("relatedPosts", webUtil.relatedPosts(tagIds));
        model.addAttribute("usedTags", webUtil.usedTags());

        return "article-info";
    }

    private boolean hasApproval(Long postsId) {
        ResultModel<Boolean> resultModel = approvalApiService.hasApproval(postsId);
        if (resultModel.getSuccess() && !ObjectUtils.isEmpty(resultModel.getData())) {
            return resultModel.getData();
        }
        return false;
    }

    private boolean hasFollow(Long followed) {
        ResultModel<Boolean> resultModel = userApiService.hasFollow(followed);
        if (!resultModel.getSuccess()) {
            return false;
        }
        return resultModel.getData();
    }

    private PageResponseModel pickComment(Model model, Long id, BasePageRequest pageRequest) {
        PageRequestModel<Long> pageRequestModel = new PageRequestModel<>();
        pageRequestModel.setPageSize(globalViewConfig.getPageSize());
        pageRequestModel.setPageNo(pageRequest.getPageNo());
        pageRequestModel.setFilter(id);

        ResultModel<PageResponseModel<CommentPageResponse>> resultModel = commentApiService.page(pageRequestModel);
        if (!resultModel.getSuccess()) {
            throw ViewException.build(resultModel.getMessage());
        }

        PageResponseModel<CommentPageResponse> pageResponseModel = resultModel.getData();
        model.addAttribute("comments", pageResponseModel.getList());

        return pageResponseModel;
    }

    private Map<String, Object> pager(Integer pageNo, PageResponseModel pageResponseModel) {
        String queryPath = "?" + WebConst.PAGE_NO_NAME + "=";

        return webUtil.buildPager(pageNo, queryPath, pageResponseModel);
    }

    private List<Map<String, Object>> authArticleList(Long authorId) {
        List<Map<String, Object>> postsList = new ArrayList<>();

        PageRequestModel<ArticleAuthorPageRequest> pageRequestModel = new PageRequestModel<>();
        pageRequestModel.setPageSize(10);
        pageRequestModel.setPageNo(1);
        pageRequestModel.setFilter(ArticleAuthorPageRequest.builder()
                .userId(authorId)
                .build());
        ResultModel<PageResponseModel<ArticleUserPageResponse>> resultModel = articleApiService.authorPage(pageRequestModel);
        if (!resultModel.getSuccess()) {
            return postsList;
        }

        PageResponseModel<ArticleUserPageResponse> pageResponseModel = resultModel.getData();
        SafesUtil.ofList(pageResponseModel.getList()).forEach(articleUserPageResponse -> {
            Map<String, Object> posts = new HashMap<>();
            posts.put("id", articleUserPageResponse.getId());
            posts.put("title", articleUserPageResponse.getTitle());
            posts.put("createdAt", articleUserPageResponse.getCreateAt());
            postsList.add(posts);
        });

        return postsList;
    }

}
