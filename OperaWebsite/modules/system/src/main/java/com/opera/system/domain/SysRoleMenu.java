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
    * 角色权限关系表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role_menu")
public class SysRoleMenu implements Serializable {
    /**
     * 角色权限编号
     */
    @TableId(value = "role_menu_id", type = IdType.ASSIGN_ID)
    private Long roleMenuId;

    /**
     * 角色编号
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限编号
     */
    @TableField(value = "menu_id")
    private Long menuId;

    @Serial
    private static final long serialVersionUID = 1L;
}