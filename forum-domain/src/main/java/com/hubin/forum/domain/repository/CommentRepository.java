package com.hubin.forum.domain.repository;

import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Comment;

import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/11/5
 * @desc
 **/
public interface CommentRepository {

    void save(Comment comment);

    Comment get(Long id);

    List<Comment> queryByPostsId(Long postsId);

    List<Comment> queryInReplyIds(Set<Long> replyIds);

    PageResult<Comment> page(Integer pageNo, Integer pageSize, Long postsId);

    void deleteByPostsId(Long postsId);

    List<Comment> queryInIds(Set<Long> ids);
}
