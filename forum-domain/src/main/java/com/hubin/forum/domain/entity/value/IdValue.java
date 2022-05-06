package com.hubin.forum.domain.entity.value;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.hubin.forum.common.enums.IdValueTypeEn;

import java.io.Serializable;

/**
 * @author Hubin
 * @create 2021/10/22
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IdValue implements Serializable {

    private String id;

    private IdValueTypeEn type;

}
