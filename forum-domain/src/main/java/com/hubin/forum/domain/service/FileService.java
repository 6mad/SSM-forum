package com.hubin.forum.domain.service;

/**
 * @author Hubin
 * @create 2021/11/23
 * @desc
 **/
public interface FileService {

    String uploadImg(byte[] base64, String fileName);
}
