package com.opera.opera.domain.vo;

import com.opera.opera.domain.OperaVideo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class OperaVideoVO extends OperaVideo {
    // 戏曲类型名称
    private String typeName;
    // 戏曲上传者id
    private Long playgoerId;
    // 戏曲上传者名称
    private String playgoerName;
    // 戏曲上传者简介
    private String playgoerInfo;
    // 戏曲上传者头像
    private String playgoerAvatar;
}
