package com.opera.opera.domain.vo;

import com.opera.opera.domain.CollectionAudio;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionAudioVO extends CollectionAudio {
    // 戏曲音频名称
    private String filename;
    // 戏曲类型
    private String typeName;
}
