package com.hubin.forum.infrastructure.dal.dao;

import com.hubin.forum.infrastructure.dal.dataobject.CommentDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @author Hubin
 * @create 2021/11/5
 * @desc
 **/
public interface CommentDAO {

    CommentDO findByPrimaryKey(@Param("id") Long id);

    List<CommentDO> findByIds(@Param("ids") Set<Long> ids);

    void insert(CommentDO commentDO);

    void update(CommentDO commentDO);

    List<CommentDO> queryByPostsId(@Param("postsId") Long postsId);

    List<CommentDO> queryInReplyIds(@Param("commentIds") List<Long> commentIds);

    void deleteByPostsId(@Param("postsId") Long id);

    List<CommentDO> queryInIds(@Param("ids") Set<Long> ids);
}
