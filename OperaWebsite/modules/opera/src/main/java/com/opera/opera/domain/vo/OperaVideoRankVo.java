package com.opera.opera.domain.vo;

import com.opera.opera.domain.OperaVideo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OperaVideoRankVo extends OperaVideo{

    //评论数
    private Integer commentNum;

}
