package com.hubin.forum.domain.service;

import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Posts;
import com.hubin.forum.domain.entity.Search;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
public interface SearchService {

    void deleteByPostsId(Long postsId);

    void save(Search search);

    PageResult<Posts> pagePosts(PageRequest<String> pageRequest);

}
