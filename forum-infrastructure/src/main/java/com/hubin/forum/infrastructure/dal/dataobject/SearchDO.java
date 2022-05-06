package com.hubin.forum.infrastructure.dal.dataobject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hubin
 * @create 2021/12/2
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchDO extends BaseDO {

    private String type;

    private Long entityId;

    private String title;

    private String content;

}
