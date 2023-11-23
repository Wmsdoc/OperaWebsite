package com.opera.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 权限表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu_item")
public class SysMenuItem implements Serializable {
    /**
     * 权限编号
     */
    @TableId(value = "item_id", type = IdType.INPUT)
    private Long itemId;

    /**
     * 权限描述
     */
    @TableField(value = "item_remark")
    private String itemRemark;

    /**
     * 权限路径
     */
    @TableField(value = "item_url")
    private String itemUrl;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    /**
     * 创建人
     */
    @TableField(value = "created_by")
    private Long createdBy;

    /**
     * 修改人
     */
    @TableField(value = "updated_by")
    private Long updatedBy;

    @Serial
    private static final long serialVersionUID = 1L;
}