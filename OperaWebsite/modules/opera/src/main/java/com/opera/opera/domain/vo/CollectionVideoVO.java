package com.opera.opera.domain.vo;

import com.opera.opera.domain.CollectionVideo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CollectionVideoVO extends CollectionVideo {
    // 戏曲音频名称
    private String filename;
    // 戏曲类型
    private String typeName;
}
