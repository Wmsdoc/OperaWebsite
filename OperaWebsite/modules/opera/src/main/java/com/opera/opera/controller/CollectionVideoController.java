package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.domain.CollectionVideo;
import com.opera.opera.service.CollectionVideoService;
import com.opera.opera.service.impl.CollectionVideoServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏迷戏曲视频收藏关系表(collection_video)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/video/collection")
public class CollectionVideoController {
    /**
     * 服务对象
     */
    @Resource
    private CollectionVideoService collectionVideoService;

    /**
     * 根据id统计收藏数
     */
    @GetMapping("/count/{videoId}")
    public SaResult count(@PathVariable("videoId") Long videoId) {
        return SaResult.data(collectionVideoService.countById(videoId));
    }

    /**
     * 根据id判断是否收藏
     */
    @GetMapping("/isCollection")
    public SaResult isCollection(@RequestParam("videoId") Long videoId, @RequestParam("playgoerId") Long playgoerId) {
        return SaResult.data(collectionVideoService.isCollection(videoId, playgoerId));
    }

    /**
     * 根据id收藏视频
     */
    @PostMapping("/insert/{videoId}/{playgoerId}")
    public SaResult collection(@PathVariable("videoId") Long videoId, @PathVariable("playgoerId") Long playgoerId) {
        return SaResult.data(collectionVideoService.insert(videoId, playgoerId));
    }

    /**
     * 根据id取消收藏视频
     */
    @DeleteMapping("/delete")
    public SaResult delete(@RequestParam("videoId") Long videoId, @RequestParam("playgoerId") Long playgoerId) {
        return SaResult.data(collectionVideoService.delete(videoId, playgoerId));
    }
}
