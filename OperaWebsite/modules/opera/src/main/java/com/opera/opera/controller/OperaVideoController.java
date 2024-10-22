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
        return SaResult.data(operaVideoService.selectVOById(videoId));
    }

    /**
     * @Role("admin")
     * @Role("user") 根据前端传入参数，进行筛选
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
     * @Role("admin")
     * @Role("user") 根据前端传入参数，进行筛选
     */
    @GetMapping("getByPageAndParamsByUser")
    public SaResult getByPageAndParamsByUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "typeId", defaultValue = "0", required = false) Integer typeId,
                                       @RequestParam(value = "timeFlag", defaultValue = "0", required = false) Integer timeFlag,
                                       @RequestParam(value = "filename", required = false) String filename) throws ParseException {
        return SaResult.data(operaVideoService.getByPageAndParamsByUser(pageNum, pageSize, typeId, timeFlag, filename));
    }

    /**
     * 获取视频下载排行
     */
    @GetMapping("getDownloadRank")
    public SaResult getDownloadRank(@RequestParam("time") String time) {
        return SaResult.data(operaVideoService.getDownloadRank(time));
    }

    /**
     * 获取视频评论排行
     */
    @GetMapping("getCommentRank")
    public SaResult getCommentRank(@RequestParam("time") String time) {
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

    /**
     * 修改
     */
    @PutMapping("/update")
    public SaResult update(@RequestParam("videoId") Long videoId,
                           @RequestParam(value = "filename", required = false) String filename,
                           @RequestParam(value = "videoInfo", required = false) String videoInfo,
                           @RequestParam(value = "typeId", required = false) Long typeId,
                           @RequestParam(value = "isExamine", required = false) Integer isExamine) {
        return SaResult.data(operaVideoService.update(videoId, filename, videoInfo,typeId, isExamine));
    }

    /**
     * 根据id删除音频
     */
    @DeleteMapping("/delete/{videoId}")
    public SaResult delete(@PathVariable Long videoId) {
        return SaResult.data(operaVideoService.removeById(videoId));
    }

    /**
     * @Role("user") 用户查询自己上传的音频
     */
    @GetMapping("getVideoByCreated")
    public SaResult getVideoByCreated(@RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
        return SaResult.data(operaVideoService.getVideoByCreated(pageNum, pageSize));
    }
}
