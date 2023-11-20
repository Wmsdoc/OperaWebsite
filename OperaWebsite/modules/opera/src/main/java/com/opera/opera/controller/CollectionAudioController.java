package com.opera.opera.controller;

import com.opera.opera.domain.CollectionAudio;
import com.opera.opera.service.impl.CollectionAudioServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏迷戏曲音频收藏关系表(collection_audio)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/collection_audio")
public class CollectionAudioController {
    /**
     * 服务对象
     */
    @Resource
    private CollectionAudioServiceImpl collectionAudioServiceImpl;



}
