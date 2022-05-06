package com.hubin.forum.infrastructure.dal.dataobject;

import com.hubin.forum.common.enums.IsDeletedEn;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Hubin
 * @create 22/2/1220/7/23
 * @desc
 **/
@Data
public class BaseDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    private Long id;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 创建时间
     */
    private Date createAt;

    /**
     * 更新时间
     */
    private Date updateAt;

    public void initBase() {
        this.id = null;
        this.isDelete = IsDeletedEn.NOT_DELETED.getValue();
        this.createAt = new Date();
        this.updateAt = this.createAt;
    }
}