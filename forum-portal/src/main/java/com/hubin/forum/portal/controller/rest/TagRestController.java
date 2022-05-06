package com.hubin.forum.portal.controller.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hubin.forum.api.model.ResultModel;
import com.hubin.forum.api.response.tag.TagQueryResponse;
import com.hubin.forum.api.service.TagApiService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hubin
 * @create 2021/11/13
 * @desc
 **/
@RestController
@RequestMapping("/tag-rest")
public class TagRestController {

    @Resource
    private TagApiService tagApiService;

    @PostMapping("/all")
    public ResultModel<List<TagQueryResponse>> all() {
        return tagApiService.queryAll();
    }
}
