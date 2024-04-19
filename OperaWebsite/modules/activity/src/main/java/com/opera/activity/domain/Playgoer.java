package com.opera.activity.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 戏迷个人信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "playgoer")
public class Playgoer implements Serializable {
    /**
     * 戏迷编号
     */
    @TableId(value = "playgoer_id", type = IdType.ASSIGN_ID)
    private Long playgoerId;

    /**
     * 戏迷用户名（4-20位）
     */
    @TableField(value = "playgoer_name")
    private String playgoerName;

    /**
     * 戏迷个人简介
     */
    @TableField(value = "playgoer_info")
    private String playgoerInfo;

    /**
     * 戏迷性别 （0 女 1 男）
     */
    @TableField(value = "playgoer_sex")
    private Integer playgoerSex;

    /**
     * 戏迷出生年月
     */
    @TableField(value = "playgoer_birthday")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date playgoerBirthday;

    /**
     * 戏迷头像ID
     */
    @TableField(value = "playgoer_avatar")
    private String playgoerAvatar;

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