package com.opera.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
    * 账号角色关系表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_account_role")
public class SysAccountRole implements Serializable {
    /**
     * 账号角色编号
     */
    @TableId(value = "account_role_id", type = IdType.INPUT)
    private Long accountRoleId;

    /**
     * 账号编号
     */
    @TableField(value = "account_id")
    private Long accountId;

    /**
     * 角色编号
     */
    @TableField(value = "role_id")
    private Long roleId;

    @Serial
    private static final long serialVersionUID = 1L;
}