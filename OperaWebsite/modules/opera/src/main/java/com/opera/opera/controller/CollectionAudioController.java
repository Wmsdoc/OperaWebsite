package com.opera.opera.controller;

import com.opera.opera.service.CollectionAudioService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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
    private CollectionAudioService collectionAudioService;

    /**
     * 根据id统计收藏数
     */
    @GetMapping("/count")
    public Long count(@RequestParam("audioId") Long audioId) {
        return collectionAudioService.countById(audioId);
    }

}
