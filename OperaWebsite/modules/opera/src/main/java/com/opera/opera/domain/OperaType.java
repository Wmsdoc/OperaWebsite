package com.opera.opera.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

/**
    * 戏曲类型表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "opera_type")
public class OperaType implements Serializable {
    /**
     * 类型编号
     */
    @TableId(value = "type_id", type = IdType.ASSIGN_ID)
    private Long typeId;

    /**
     * 类型名称
     */
    @TableField(value = "type_name")
    private String typeName;

    /**
     * 是否删除 （默认0 未删除 1 已删除）
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