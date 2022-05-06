package com.hubin.forum.infrastructure.transfer;

import org.springframework.util.ObjectUtils;
import com.hubin.forum.common.enums.ArticleTypeScopeEn;
import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.domain.entity.ArticleType;
import com.hubin.forum.infrastructure.dal.dataobject.ArticleTypeDO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
public class ArticleTypeTransfer {

    public static ArticleTypeDO toArticleTypeDO(ArticleType articleType) {
        ArticleTypeDO articleTypeDO = ArticleTypeDO.builder()
                .auditState(ObjectUtils.isEmpty(articleType.getAuditState()) ? null : articleType.getAuditState().getValue())
                .creatorId(articleType.getCreatorId())
                .description(articleType.getDescription())
                .name(articleType.getName())
                .refCount(articleType.getRefCount())
                .scope(ObjectUtils.isEmpty(articleType.getScope()) ? null : articleType.getScope().getValue())
                .build();

        articleTypeDO.initBase();

        return articleTypeDO;
    }

    public static List<ArticleType> toArticleTypes(List<ArticleTypeDO> articleTypeDOS) {
        List<ArticleType> articleTypes = new ArrayList<>();
        if (ObjectUtils.isEmpty(articleTypeDOS)) {
            return articleTypes;
        }

        articleTypeDOS.forEach(articleTypeDO -> articleTypes.add(toArticleType(articleTypeDO)));

        return articleTypes;
    }

    public static ArticleType toArticleType(ArticleTypeDO articleTypeDO) {
        ArticleType articleType = ArticleType.builder()
                .auditState(AuditStateEn.getEntity(articleTypeDO.getAuditState()))
                .creatorId(articleTypeDO.getCreatorId())
                .description(articleTypeDO.getDescription())
                .name(articleTypeDO.getName())
                .refCount(articleTypeDO.getRefCount())
                .scope(ArticleTypeScopeEn.getEntity(articleTypeDO.getScope()))
                .build();
        articleType.setId(articleTypeDO.getId());
        articleType.setCreatorId(articleTypeDO.getCreatorId());
        articleType.setCreateAt(articleTypeDO.getCreateAt());
        articleType.setUpdateAt(articleTypeDO.getUpdateAt());

        return articleType;
    }
}
