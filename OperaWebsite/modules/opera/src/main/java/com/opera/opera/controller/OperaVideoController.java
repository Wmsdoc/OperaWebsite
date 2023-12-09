package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.common.core.domain.R;
import com.opera.opera.service.OperaVideoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


/**
 * 戏曲视频表(opera_video)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/video")
public class OperaVideoController {
    /**
     * 服务对象
     */
    @Resource
    private OperaVideoService operaVideoService;

    /**
     * 根据ID查询视频详情
     */
    @GetMapping("getById")
    public SaResult getById(@RequestParam("videoId") Long videoId) {
        return SaResult.data(operaVideoService.selectById(videoId));
    }

    /**
     * 根据前端传入参数，进行筛选
     */
    @GetMapping("getByPageAndParams")
    public SaResult getByPageAndParams(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "typeId", defaultValue = "0", required = false) Integer typeId,
                                       @RequestParam(value = "timeFlag", defaultValue = "0", required = false) Integer timeFlag,
                                       @RequestParam(value = "filename", required = false) String filename) throws ParseException {
        return SaResult.data(operaVideoService.selectByPageAndParams(pageNum, pageSize, typeId, timeFlag, filename));
    }

    /**
     *  获取视频下载排行
     */
    @GetMapping("getDownloadRank")
    public SaResult getDownloadRank(@RequestParam("time")String time) {
        return SaResult.data(operaVideoService.getDownloadRank(time));
    }

    /**
     * 获取视频评论排行
     */
    @GetMapping("getCommentRank")
    public SaResult getCommentRank(@RequestParam("time")String time) {
        return SaResult.data(operaVideoService.getCommentRank(time));
    }

    /**
     * 远程调用 新增视频
     */
    @PostMapping("insert")
    public R<Boolean> insert(@RequestParam("accountId") Long accountId, @RequestParam("filename") String filename,
                             @RequestParam("videoInfo") String videoInfo, @RequestParam("videoUrl") String videoUrl,
                             @RequestParam("typeId") Long typeId) {
        return R.ok(operaVideoService.insert(accountId, filename, videoInfo, videoUrl, typeId));
    }

    /**
     * 新增下载量
     */
    @PostMapping("addDownloadNum/{videoId}")
    public Boolean addDownloadNum(@PathVariable("videoId") Long videoId) {
        return operaVideoService.addDownloadNum(videoId);
    }
}
