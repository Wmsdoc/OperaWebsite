package com.opera.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Advert {
    // 广告id
    private String advertId;
    // 广告内容
    private String advertContent;
    // 广告创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date advertCreatedAt;
    // 广告图片地址
    private String imgUrl;
    // 广告链接地址
    private String linkUrl;
}
