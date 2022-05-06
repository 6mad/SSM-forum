package com.hubin.forum.app.manager;

import com.alibaba.fastjson.JSON;
import com.hubin.forum.app.support.IsLogin;
import com.hubin.forum.app.support.PageUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import com.hubin.forum.api.model.PageRequestModel;
import com.hubin.forum.api.model.PageResponseModel;
import com.hubin.forum.api.request.AdminBooleanRequest;
import com.hubin.forum.api.request.tag.TagCreateRequest;
import com.hubin.forum.api.request.tag.TagPageRequest;
import com.hubin.forum.api.response.tag.TagPageResponse;
import com.hubin.forum.api.response.tag.TagQueryResponse;
import com.hubin.forum.api.vo.PostsVO;
import com.hubin.forum.app.transfer.TagTransfer;
import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.common.enums.CacheBizTypeEn;
import com.hubin.forum.common.enums.ErrorCodeEn;
import com.hubin.forum.common.enums.UserRoleEn;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.common.support.CheckUtil;
import com.hubin.forum.common.support.SafesUtil;
import com.hubin.forum.domain.entity.Posts;
import com.hubin.forum.domain.entity.Tag;
import com.hubin.forum.domain.repository.TagRepository;
import com.hubin.forum.domain.service.CacheService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Hubin
 * @create 2021/7/31
 * @desc
 **/
@Component
public class TagManager extends AbstractPostsManager {

    @Resource
    private TagRepository tagRepository;

    @Resource
    private CacheService cacheService;

    private static final String ALL_USED_TAG_CACHE_KEY = "all_used_tag";

    /**
     * 1小时
     */
    private static final Long TAG_CACHE_EXPIRE_SECOND = 60 * 60L;

    @IsLogin(role = UserRoleEn.ADMIN)
    public void create(TagCreateRequest request) {
        CheckUtil.isNotEmpty(tagRepository.query(Tag.builder().name(request.getName()).build()), ErrorCodeEn.TAG_IS_EXIST);

        tagRepository.save(TagTransfer.toTag(request));
    }

    public List<TagQueryResponse> queryAll() {
        return TagTransfer.toTagQueryAllResponses(tagRepository.queryByState(AuditStateEn.PASS));
    }

    public List<TagQueryResponse> queryInIds(Set<Long> ids) {
        List<Tag> tags = tagRepository.queryByIds(ids);

        return TagTransfer.toTagQueryAllResponses(SafesUtil.ofList(tags).stream()
                .filter(tag -> AuditStateEn.PASS.equals(tag.getAuditState()))
                .collect(Collectors.toList()));
    }

    public TagQueryResponse getByName(String name) {
        Tag tag = tagRepository.getByNameAndState(name, AuditStateEn.PASS);
        CheckUtil.isEmpty(tag, ErrorCodeEn.TAG_NOT_EXIST);

        return TagTransfer.toTagQueryAllResponse(tag);
    }

    public PageResponseModel<PostsVO> pagePosts(PageRequestModel<Long> pageRequestModel) {
        PageResult<Posts> pageResult = tagRepository.pagePosts(PageUtil.buildPageRequest(pageRequestModel, pageRequestModel.getFilter()));

        return pagePostsVO(pageResult);
    }

    public PageResponseModel<PostsVO> pagePostsByTagIds(PageRequestModel<Set<Long>> pageRequestModel) {
        PageResult<Posts> pageResult = tagRepository.pagePostsByTagIds(PageUtil.buildPageRequest(pageRequestModel, pageRequestModel.getFilter()));

        return pagePostsVO(pageResult);
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public PageResponseModel<TagPageResponse> page(PageRequestModel<TagPageRequest> pageRequestModel) {
        TagPageRequest tagPageRequest = pageRequestModel.getFilter();
        Tag tag = Tag.builder()
                .groupName(tagPageRequest.getGroupName())
                .name(tagPageRequest.getName())
                .description(tagPageRequest.getDescription())
                .build();
        if (!ObjectUtils.isEmpty(tagPageRequest.getAuditState())) {
            tag.setAuditState(AuditStateEn.getEntity(tagPageRequest.getAuditState()));
        }

        PageResult<Tag> pageResult = tagRepository.page(PageUtil.buildPageRequest(pageRequestModel, tag));

        return PageResponseModel.build(pageResult.getTotal(), pageResult.getSize(), TagTransfer.toTagPageResponses(pageResult.getList()));
    }

    @IsLogin(role = UserRoleEn.ADMIN)
    public void tagAuditState(AdminBooleanRequest booleanRequest) {
        Tag tag = tagRepository.get(booleanRequest.getId());
        CheckUtil.isEmpty(tag, ErrorCodeEn.TAG_NOT_EXIST);

        tag.setAuditState(booleanRequest.getIs() ? AuditStateEn.PASS : AuditStateEn.REJECT);
        tagRepository.update(tag);
    }

    public List<TagQueryResponse> queryAllRef() {
        String cache = cacheService.get(CacheBizTypeEn.TAG_USED, ALL_USED_TAG_CACHE_KEY);

        List<Tag> cacheTags;
        if (ObjectUtils.isEmpty(cache)) {
            List<Tag> tags = tagRepository.queryByState(AuditStateEn.PASS);
            cacheTags = SafesUtil.ofList(tags).stream().filter(tag -> tag.getRefCount() > 0).collect(Collectors.toList());
            cacheService.setAndExpire(CacheBizTypeEn.TAG_USED, ALL_USED_TAG_CACHE_KEY, JSON.toJSONString(cacheTags), TAG_CACHE_EXPIRE_SECOND);
        } else {
            cacheTags = JSON.parseArray(cache, Tag.class);
        }

        return TagTransfer.toTagQueryAllResponses(cacheTags);
    }
}
