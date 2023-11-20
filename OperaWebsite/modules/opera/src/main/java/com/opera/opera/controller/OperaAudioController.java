package com.opera.opera.controller;

import com.opera.opera.domain.OperaAudio;
import com.opera.opera.service.impl.OperaAudioServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏曲音频表(opera_audio)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/opera_audio")
public class OperaAudioController {
    /**
     * 服务对象
     */
    @Resource
    private OperaAudioServiceImpl operaAudioServiceImpl;



}
