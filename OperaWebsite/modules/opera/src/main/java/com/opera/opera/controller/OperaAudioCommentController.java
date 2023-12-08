package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.service.OperaAudioCommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏曲音频评论表(opera_audio_comment)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/audio/comment")
public class OperaAudioCommentController {
    /**
     * 服务对象
     */
    @Resource
    private OperaAudioCommentService operaAudioCommentService;

    /**
     * 根据 音频id 分页查询评论信息
     */
    @GetMapping("/getComment")
    public SaResult getComment(@RequestParam("audioId") Long audioId, @RequestParam("page") Integer page) {
        return SaResult.data(operaAudioCommentService.getCommentById(audioId, page));
    }

    /**
     * 根据id新增评论
     */
    @PostMapping("/insert/{audioId}/{content}")
    public SaResult insert(@PathVariable("audioId") Long audioId, @PathVariable("content") String content) {
        return SaResult.data(operaAudioCommentService.insert(audioId, content));
    }

    /**
     * 根据id删除评论
     */
    @DeleteMapping("/delete/{commentId}")
    public SaResult delete(@PathVariable Long commentId) {
        return SaResult.data(operaAudioCommentService.delete(commentId));
    }

    /**
     * 根据id删除评论
     */
    @PutMapping("/update")
    public SaResult update(@RequestParam("commentId") Long commentId,@RequestParam("commentInfo")String commentInfo) {
        return SaResult.data(operaAudioCommentService.update(commentId,commentInfo));
    }

    /**
     * 获取用户Id 获取戏曲音频 列表
     */
    @PostMapping("/getCommentByPlaygoerId/{playgoerId}/{pageNum}/{pageSize}")
    public SaResult getCommentByPlaygoerId(@PathVariable Long playgoerId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return SaResult.data(operaAudioCommentService.getCommentByPlaygoerId(playgoerId, pageNum, pageSize));
    }
}
