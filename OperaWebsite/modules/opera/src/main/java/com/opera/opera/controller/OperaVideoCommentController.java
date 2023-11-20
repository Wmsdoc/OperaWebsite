package com.opera.opera.controller;

import com.opera.opera.domain.OperaVideoComment;
import com.opera.opera.service.impl.OperaVideoCommentServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏曲视频评论表(opera_video_comment)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/opera_video_comment")
public class OperaVideoCommentController {
    /**
     * 服务对象
     */
    @Resource
    private OperaVideoCommentServiceImpl operaVideoCommentServiceImpl;



}
