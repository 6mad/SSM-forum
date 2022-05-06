package com.hubin.forum.infrastructure.dal.dao;

import com.hubin.forum.infrastructure.dal.dataobject.UserFollowDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Hubin
 * @create 22/2/1220/11/19
 * @desc
 **/
public interface UserFollowDAO {

    void insert(UserFollowDO userFollowDO);

    List<UserFollowDO> query(UserFollowDO userFollowDO);

    void delete(@Param("id") Long id);

    List<Long> getAllFollowerIds(@Param("follower") Long follower, @Param("type") String type);
}
