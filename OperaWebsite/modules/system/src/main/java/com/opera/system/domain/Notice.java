package com.opera.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 网站公告管理
 */
@Data
public class Notice implements Serializable {
    // 公告id
    private String noticeId;
    // 公告内容
    private String noticeContent;
    // 公告创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date noticeCreatedAt;
}
