package com.opera.opera.domain.vo;

import com.opera.opera.domain.OperaAudioComment;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class OperaAudioCommentVO extends OperaAudioComment {
    // 评论者id
    private Long playgoerId;
    // 评论者名称
    private String playgoerName;
    // 评论者
    private String playgoerInfo;
    // 戏曲上传者头像
    private String playgoerAvatar;
}