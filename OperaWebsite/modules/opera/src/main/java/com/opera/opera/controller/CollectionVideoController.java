package com.opera.opera.controller;

import com.opera.opera.domain.CollectionVideo;
import com.opera.opera.service.impl.CollectionVideoServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏迷戏曲视频收藏关系表(collection_video)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/collection_video")
public class CollectionVideoController {
    /**
     * 服务对象
     */
    @Resource
    private CollectionVideoServiceImpl collectionVideoServiceImpl;


}
