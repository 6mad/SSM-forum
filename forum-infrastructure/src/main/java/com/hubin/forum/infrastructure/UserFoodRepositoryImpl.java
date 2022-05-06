package com.hubin.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubin.forum.infrastructure.dal.dao.UserFoodDAO;
import com.hubin.forum.infrastructure.dal.dataobject.UserFoodDO;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.Posts;
import com.hubin.forum.domain.entity.UserFood;
import com.hubin.forum.domain.repository.UserFoodRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/12/3
 * @desc
 **/
@Repository
public class UserFoodRepositoryImpl extends AbstractPostsRepository implements UserFoodRepository {

    @Resource
    private UserFoodDAO userFoodDAO;

    @Override
    public void batchSave(List<UserFood> userFoods) {
        if (ObjectUtils.isEmpty(userFoods)) {
            return;
        }

        userFoods.forEach(userFood -> {
            try {
                UserFoodDO userFoodDO = UserFoodDO.builder()
                        .userId(userFood.getUserId())
                        .postsId(userFood.getPostsId())
                        .build();
                userFoodDO.initBase();

                userFoodDAO.insert(userFoodDO);
            } catch (Exception e) {
                // 唯一健冲突忽略
            }
        });
    }

    @Override
    public PageResult<Posts> pagePosts(PageRequest<Long> pageRequest) {
        PageHelper.startPage(pageRequest.getPageNo(), pageRequest.getPageSize());

        List<UserFoodDO> userFoodDOS = userFoodDAO.query(pageRequest.getFilter());
        PageInfo<UserFoodDO> pageInfo = new PageInfo<>(userFoodDOS);

        if (ObjectUtils.isEmpty(userFoodDOS)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        List<Long> postsIds = new ArrayList<>();
        userFoodDOS.forEach(userFoodDO -> postsIds.add(userFoodDO.getPostsId()));

        return basePagePosts(postsIds, pageInfo, null);
    }

    @Override
    public void deleteByPostsId(Long postsId) {
        userFoodDAO.deleteByPostsId(postsId);
    }
}
