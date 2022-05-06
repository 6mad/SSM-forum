package com.hubin.forum.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author Hubin
 * @create 22/2/1220/7/23
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseModel<T> implements Serializable {

    private List<T> list;
    private Long total;
    private Integer size;

    public static <T> PageResponseModel<T> build(Long total, Integer size, List<T> list) {
        PageResponseModel<T> result = new PageResponseModel<>();
        result.setSize(size);
        result.setTotal(total);
        result.setList(list);

        return result;
    }

}