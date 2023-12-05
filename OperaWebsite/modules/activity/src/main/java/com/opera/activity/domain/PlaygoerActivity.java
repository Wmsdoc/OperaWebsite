package com.opera.activity.domain;

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
 * 戏迷活动关系表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "playgoer_activity")
public class PlaygoerActivity implements Serializable {
    /**
     * 戏迷活动编号
     */
    @TableId(value = "playgoer_activity_id", type = IdType.ASSIGN_ID)
    private Long playgoerActivityId;

    /**
     * 活动编号
     */
    @TableField(value = "activity_id")
    private Long activityId;

    /**
     * 戏迷编号
     */
    @TableField(value = "playgoer_id")
    private Long playgoerId;

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