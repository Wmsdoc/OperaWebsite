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
    * 戏迷账号表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_account")
public class SysAccount implements Serializable {
    /**
     * 账号编号
     */
    @TableId(value = "account_id", type = IdType.INPUT)
    private Long accountId;

    /**
     * 戏迷登录用户名（4-16位）
     */
    @TableField(value = "account_username")
    private String accountUsername;

    /**
     * 戏迷登录密码（4-16位）

     */
    @TableField(value = "account_password")
    private String accountPassword;

    /**
     * 账号状态
     */
    @TableField(value = "account_statue")
    private Integer accountStatue;

    /**
     * 戏迷个人信息编号
     */
    @TableField(value = "playgoer_id")
    private Long playgoerId;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 账号创建时间
     */
    @TableField(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 账号更新时间
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