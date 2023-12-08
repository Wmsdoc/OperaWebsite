package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.service.CollectionAudioService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;


/**
 * 戏迷戏曲音频收藏关系表(collection_audio)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/audio/collection")
public class CollectionAudioController {
    /**
     * 服务对象
     */
    @Resource
    private CollectionAudioService collectionAudioService;

    /**
     * 根据id统计收藏数
     */
    @GetMapping("/count/{audioId}")
    public SaResult count(@PathVariable Long audioId) {
        return SaResult.data(collectionAudioService.countById(audioId));
    }

    /**
     * 根据id判断是否收藏
     */
    @GetMapping("/isCollection")
    public SaResult isCollection(@RequestParam("audioId") Long audioId, @RequestParam("playgoerId") Long playgoerId) {
        return SaResult.data(collectionAudioService.isCollection(audioId, playgoerId));
    }

    /**
     * 根据id收藏音频
     */
    @PostMapping("/insert/{audioId}/{playgoerId}")
    public SaResult collection(@PathVariable("audioId") Long audioId, @PathVariable("playgoerId") Long playgoerId) {
        return SaResult.data(collectionAudioService.insert(audioId, playgoerId));
    }

    /**
     * 根据戏曲id和用户id取消收藏音频
     */
    @DeleteMapping("/delete")
    public SaResult delete(@RequestParam("audioId") Long audioId, @RequestParam("playgoerId") Long playgoerId) {
        return SaResult.data(collectionAudioService.delete(audioId, playgoerId));
    }

    /**
     * 根据token获取用户收藏的戏曲
     */
    @PostMapping("/getCollectByPlaygoerId/{pageNum}/{pageSize}")
    public SaResult getCollectByPlaygoerId(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return SaResult.data(collectionAudioService.getCollectByPlaygoerId(pageNum, pageSize));
    }
}
