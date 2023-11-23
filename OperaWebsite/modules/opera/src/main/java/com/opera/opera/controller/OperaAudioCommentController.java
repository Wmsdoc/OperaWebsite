package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.domain.OperaAudioComment;
import com.opera.opera.service.OperaAudioCommentService;
import com.opera.opera.service.impl.OperaAudioCommentServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏曲音频评论表(opera_audio_comment)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/opera_audio_comment")
public class OperaAudioCommentController {
    /**
     * 服务对象
     */
    @Resource
    private OperaAudioCommentService operaAudioCommentService;

    /**
     * 根据id 查询评论信息
     */
    @GetMapping("/getComment")
    public SaResult getComment(@RequestParam("audioId") Long audioId) {
        return SaResult.data(operaAudioCommentService.getCommentById(audioId));
    }

}
