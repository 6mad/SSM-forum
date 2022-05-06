package com.hubin.forum.domain.repository;

import com.hubin.forum.common.enums.ArticleTypeScopeEn;
import com.hubin.forum.common.enums.AuditStateEn;
import com.hubin.forum.common.model.PageRequest;
import com.hubin.forum.common.model.PageResult;
import com.hubin.forum.domain.entity.ArticleType;

import java.util.List;

/**
 * @author Hubin
 * @create 2021/10/31
 * @desc
 **/
public interface ArticleTypeRepository {

    void save(ArticleType articleType);

    List<ArticleType> query(ArticleType articleType);

    List<ArticleType> queryByState(AuditStateEn auditState);

    List<ArticleType> queryByScopesAndState(List<ArticleTypeScopeEn> scopes, AuditStateEn auditState);

    void update(ArticleType articleType);

    ArticleType get(Long id);

    ArticleType getByNameAndState(String typeName, AuditStateEn pass);

    void increaseRefCount(Long id);

    void decreaseRefCount(Long id);

    PageResult<ArticleType> page(PageRequest<ArticleType> articleTypePageRequest);
}
