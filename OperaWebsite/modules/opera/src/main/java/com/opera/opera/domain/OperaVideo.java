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
    * 戏曲视频表
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "opera_video")
public class OperaVideo implements Serializable {
    /**
     * 戏曲编号
     */
    @TableId(value = "video_id", type = IdType.INPUT)
    private Long videoId;

    /**
     * 戏曲简介
     */
    @TableField(value = "video_info")
    private String videoInfo;

    /**
     * 类型编号
     */
    @TableField(value = "type_id")
    private Long typeId;

    /**
     * 戏曲视频下载量
     */
    @TableField(value = "download_num")
    private Integer downloadNum;

    /**
     * 视频文件名称
     */
    @TableField(value = "filename")
    private String filename;

    /**
     * 是否审核
     */
    @TableField(value = "is_examine")
    private Integer isExamine;

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