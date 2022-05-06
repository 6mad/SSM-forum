package com.hubin.forum.app.manager;

import com.hubin.forum.app.support.IsLogin;
import com.hubin.forum.app.support.LoginUserContext;
import com.hubin.forum.app.support.PageUtil;
import org.springframework.stereotype.Component;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Posts;
import com.hubin.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;

/**
 * @author Hubin
 * @create 2021/11/25
 * @desc
 **/
@Component
public class PostsManager extends AbstractPostsManager {

    @Resource
    private UserFoodRepository userFoodRepository;

    @IsLogin
    public PageResponseModel<PostsVO> pagePostsFood(PageRequestModel pageRequestModel) {
        PageResult<Posts> pageResult = userFoodRepository.pagePosts(PageUtil.buildPageRequest(pageRequestModel, LoginUserContext.getUser().getId()));

        return pagePostsVO(pageResult);
    }
}
