package com.hubin.forum.api.response.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hubin
 * @create 2021/7/31
 * @desc
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessagePageResponse implements Serializable {

    private Long id;

    private String read;

    private String sender;

    private String senderAvatar;

    private String senderName;

    private String typeDesc;

    private String title;

    private String infoId;

    private Date createAt;

}
