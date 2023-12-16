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
     * @Role("admin") 根据前端传入参数，进行筛选
     */
    @GetMapping("getByPageAndParams")
    public SaResult getByPageAndParams(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "filename", required = false) String filename,
                                       @RequestParam(value = "playgoerName", required = false) String playgoerName,
                                       @RequestParam(value = "typeId",required = false) Long typeId) {
        return SaResult.data(operaVideoCommentService.selectByPageAndParams(pageNum, pageSize, filename,playgoerName,typeId));
    }

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

    /**
     * 根据id删除评论
     */
    @DeleteMapping("/delete/{commentId}")
    public SaResult delete(@PathVariable Long commentId) {
        return SaResult.data(operaVideoCommentService.delete(commentId));
    }

    /**
     * 根据id删除评论
     */
    @PutMapping("/update")
    public SaResult update(@RequestParam("commentId") Long commentId,@RequestParam("commentInfo")String commentInfo) {
        return SaResult.data(operaVideoCommentService.update(commentId,commentInfo));
    }

    /**
     * 获取用户Id 获取戏曲音频 列表
     */
    @PostMapping("/getCommentByPlaygoerId/{playgoerId}/{pageNum}/{pageSize}")
    public SaResult getCommentByPlaygoerId(@PathVariable Long playgoerId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return SaResult.data(operaVideoCommentService.getCommentByPlaygoerId(playgoerId, pageNum, pageSize));
    }
}
