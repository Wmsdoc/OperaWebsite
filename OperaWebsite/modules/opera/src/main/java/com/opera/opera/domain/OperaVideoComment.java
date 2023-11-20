package com.opera.opera.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.*;

/**
    * 戏曲视频评论表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "opera_video_comment")
public class OperaVideoComment implements Serializable {
    /**
     * 戏曲音频评论编号
     */
    @TableId(value = "comment_id", type = IdType.INPUT)
    private Long commentId;

    /**
     * 评论者
     */
    @TableField(value = "playgoer_id")
    private Long playgoerId;

    /**
     * 评论内容
     */
    @TableField(value = "comment_info")
    private String commentInfo;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
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