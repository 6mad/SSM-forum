package com.hubin.forum.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hubin.forum.common.enums.SearchTypeEn;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Search extends BaseEntity {

    private SearchTypeEn type;

    private Long entityId;

    private String title;

    private String content;

}
