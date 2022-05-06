package com.hubin.forum.infrastructure.dal.dao;

import com.hubin.forum.infrastructure.dal.dataobject.UserFoodDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/3
 * @desc
 **/
public interface UserFoodDAO {

    void insert(UserFoodDO userFoodDO);

    List<UserFoodDO> query(@Param("userId") Long userId);

    void deleteByPostsId(@Param("postsId") Long postsId);
}
