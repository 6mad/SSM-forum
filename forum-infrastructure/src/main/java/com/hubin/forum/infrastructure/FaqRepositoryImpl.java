package com.hubin.forum.infrastructure;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hubin.forum.domain.entity.Faq;
import com.hubin.forum.domain.entity.Tag;
import com.hubin.forum.domain.entity.User;
import com.hubin.forum.infrastructure.dal.dao.PostsDAO;
import com.hubin.forum.infrastructure.dal.dao.TagDAO;
import com.hubin.forum.infrastructure.dal.dao.TagPostsMappingDAO;
import com.hubin.forum.infrastructure.dal.dao.UserDAO;
import com.hubin.forum.infrastructure.dal.dataobject.PostsDO;
import com.hubin.forum.infrastructure.dal.dataobject.TagPostsMappingDO;
import com.hubin.forum.infrastructure.dal.dataobject.UserDO;
import com.hubin.forum.infrastructure.transfer.PostsTransfer;
import com.hubin.forum.infrastructure.transfer.TagTransfer;
import com.hubin.forum.infrastructure.transfer.UserTransfer;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.common.enums.PostsCategoryEn;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.common.support.SafesUtil;
import com.hubin.forum.domain.entity.value.PostsPageQueryValue;
import com.hubin.forum.domain.repository.FaqRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 2021/11/1
 * @desc
 **/
@Repository
public class FaqRepositoryImpl implements FaqRepository {

    @Resource
    private PostsDAO postsDAO;

    @Resource
    private TagPostsMappingDAO tagPostsMappingDAO;

    @Resource
    private TagDAO tagDAO;

    @Resource
    private UserDAO userDAO;

    @Override
    public void save(Faq faq) {
        PostsDO postsDO = PostsTransfer.toPostsDO(faq);
        postsDO.setCreateAt(new Date());

        postsDAO.insert(postsDO);

        tagPostsMappingDAO.batchInsert(SafesUtil.ofSet(faq.getTags()).stream().map(tag -> {
            TagPostsMappingDO tagPostsMappingDO = TagPostsMappingDO.builder()
                    .tagId(tag.getId())
                    .postsId(postsDO.getId())
                    .build();
            tagPostsMappingDO.initBase();
            return tagPostsMappingDO;
        }).collect(Collectors.toList()));

        faq.setId(postsDO.getId());
    }

    @Override
    public void update(Faq faq) {
        postsDAO.update(PostsTransfer.toPostsDO(faq));

        List<TagPostsMappingDO> tagPostsMappingDOS = SafesUtil.ofSet(faq.getTags()).stream().map(tag -> {
            TagPostsMappingDO tagPostsMappingDO = TagPostsMappingDO.builder()
                    .postsId(faq.getId())
                    .tagId(tag.getId())
                    .build();
            tagPostsMappingDO.initBase();
            return tagPostsMappingDO;
        }).collect(Collectors.toList());
        tagPostsMappingDAO.batchInsert(tagPostsMappingDOS);
    }

    @Override
    public void updateEntity(Faq faq) {
        postsDAO.update(PostsTransfer.toPostsDO(faq));
    }

    @Override
    public Faq get(Long id) {
        PostsDO postsDO = postsDAO.get(id);
        if (ObjectUtils.isEmpty(postsDO)) {
            return null;
        }

        UserDO userDO = userDAO.get(postsDO.getAuthorId());
        if (ObjectUtils.isEmpty(userDO)) {
            return null;
        }
        User user = UserTransfer.toUser(userDO);

        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.query(TagPostsMappingDO.builder()
                .postsId(id)
                .build());
        Set<Long> tagIds = SafesUtil.ofList(tagPostsMappingDOS).stream()
                .map(TagPostsMappingDO::getTagId)
                .collect(Collectors.toSet());
        if (ObjectUtils.isEmpty(tagIds)) {
            return null;
        }

        List<Tag> tags = TagTransfer.toTags(tagDAO.queryInIds(tagIds));

        return PostsTransfer.toFaq(postsDO, user, tags);
    }

    @Override
    public PageResult<Faq> page(Integer pageNo, Integer pageSize, PostsPageQueryValue pageQueryValue) {
        PageHelper.startPage(pageNo, pageSize);

        List<PostsDO> postsDOS = postsDAO.queryByValue(pageQueryValue);
        PageInfo<PostsDO> pageInfo = new PageInfo<>(postsDOS);

        if (ObjectUtils.isEmpty(postsDOS)) {
            return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), new ArrayList<>());
        }

        Set<Long> userIds = SafesUtil.ofList(postsDOS).stream().map(PostsDO::getAuthorId).collect(Collectors.toSet());
        List<User> users = UserTransfer.toUsers(userDAO.queryInIds(userIds));

        Set<Long> postsIds = SafesUtil.ofList(postsDOS).stream().map(PostsDO::getId).collect(Collectors.toSet());
        List<TagPostsMappingDO> tagPostsMappingDOS = tagPostsMappingDAO.queryInPostsIds(postsIds);
        Set<Long> tagIds = SafesUtil.ofList(tagPostsMappingDOS).stream().map(TagPostsMappingDO::getTagId).collect(Collectors.toSet());
        List<Tag> tags = TagTransfer.toTags(tagDAO.queryInIds(tagIds));

        List<Faq> faqs = PostsTransfer.toFaqs(postsDOS, users, tagPostsMappingDOS, tags);
        return PageResult.build(pageInfo.getTotal(), pageInfo.getSize(), faqs);
    }

    @Override
    public List<Faq> hots(int size) {
        PageHelper.startPage(1, size);
        List<PostsDO> postsDOS = postsDAO.queryOrderViews(PostsCategoryEn.FAQ.getValue(), AuditStateEn.PASS.getValue());

        if (ObjectUtils.isEmpty(postsDOS)) {
            return new ArrayList<>();
        }

        return TagTransfer.toFaqs(postsDOS);
    }
}
