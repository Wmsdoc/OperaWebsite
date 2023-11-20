package com.opera.opera.controller;

import com.opera.opera.domain.OperaVideo;
import com.opera.opera.service.impl.OperaVideoServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏曲视频表(opera_video)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/opera_video")
public class OperaVideoController {
    /**
     * 服务对象
     */
    @Resource
    private OperaVideoServiceImpl operaVideoServiceImpl;



}
