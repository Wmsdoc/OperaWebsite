package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.service.OperaVideoCommentService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏曲视频评论表(opera_video_comment)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/video/comment")
public class OperaVideoCommentController {
    /**
     * 服务对象
     */
    @Resource
    private OperaVideoCommentService operaVideoCommentService;

    /**
     * 根据id 查询评论信息
     */
    @GetMapping("/getComment")
    public SaResult getComment(@RequestParam("videoId") Long videoId, @RequestParam("page") Integer page) {
        return SaResult.data(operaVideoCommentService.getCommentById(videoId, page));
    }

    /**
     * 根据id新增评论
     */
    @PostMapping("/insert/{videoId}/{content}")
    public SaResult insert(@PathVariable("videoId") Long videoId, @PathVariable("content") String content) {
        return SaResult.data(operaVideoCommentService.insert(videoId, content));
    }
}
