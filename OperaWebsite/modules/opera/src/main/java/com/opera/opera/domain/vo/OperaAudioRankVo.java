package com.opera.opera.domain.vo;

import com.opera.opera.domain.OperaAudio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OperaAudioRankVo extends OperaAudio {

    //评论数
    private Integer commentNum;

}
