package com.opera.opera.domain.vo;

import com.opera.opera.domain.OperaType;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OperaTypeVO extends OperaType {
    //该类型的音频数量
    private Integer audioCount;
    //该类型的视频数量
    private Integer videoCount;
}
