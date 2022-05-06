package com.hubin.forum.app.manager;

import com.hubin.forum.app.support.PageUtil;
import org.springframework.stereotype.Component;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Posts;
import com.hubin.forum.domain.service.SearchService;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Component
public class SearchManager extends AbstractPostsManager {

    @Resource
    private SearchService searchService;

    public PageResponseModel<PostsVO> pagePostsSearch(PageRequestModel<String> pageRequestModel) {
        PageResult<Posts> pageResult = searchService.pagePosts(PageUtil.buildPageRequest(pageRequestModel, pageRequestModel.getFilter()));

        return pagePostsVO(pageResult);
    }
}
