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
    * 戏迷戏曲音频收藏关系表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "collection_audio")
public class CollectionAudio implements Serializable {
    /**
     * 收藏音频编号
     */
    @TableId(value = "collection_id", type = IdType.ASSIGN_ID)
    private Long collectionId;

    /**
     * 戏迷编号
     */
    @TableField(value = "playgoer_id")
    private Long playgoerId;

    /**
     * 戏曲音频编号
     */
    @TableField(value = "audio_id")
    private Long audioId;

    /**
     * 是否删除
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdAt;

    /**
     * 更新时间
     */
    @TableField(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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