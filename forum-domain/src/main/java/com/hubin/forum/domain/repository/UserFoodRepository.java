package com.hubin.forum.domain.repository;

import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Posts;
import com.hubin.forum.domain.entity.UserFood;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/3
 * @desc
 **/
public interface UserFoodRepository {

    void batchSave(List<UserFood> userFoods);

    PageResult<Posts> pagePosts(PageRequest<Long> pageRequest);

    void deleteByPostsId(Long postsId);
}
