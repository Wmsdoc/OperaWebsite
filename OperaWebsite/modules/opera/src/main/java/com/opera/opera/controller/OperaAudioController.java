package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.service.OperaAudioService;
import com.opera.opera.service.impl.OperaAudioServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;


/**
 * 戏曲音频表(opera_audio)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/audio")
public class OperaAudioController {
    /**
     * 服务对象
     */
    @Resource
    private OperaAudioService operaAudioService;

    /**
     * 根据前端传入参数，进行筛选
     */
    @GetMapping("getByPageAndParams")
    public SaResult getByPageAndParams(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                                       @RequestParam(value = "timeFlag",required = false) int timeFlag,
                                       OperaAudio operaAudio) throws ParseException {
        return SaResult.data(operaAudioService.selectByPageAndParams(pageNum, pageSize, timeFlag,operaAudio));
    }

}
