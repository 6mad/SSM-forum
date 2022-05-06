package com.hubin.forum.api.request.config;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Hubin
 * @create 2021/12/26
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConfigPageRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private String state;

    private String type;

    private String name;

}
