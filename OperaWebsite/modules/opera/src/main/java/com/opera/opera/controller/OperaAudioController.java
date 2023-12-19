package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.common.core.domain.R;
import com.opera.opera.service.OperaAudioService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


/**
 * 戏曲音频表(opera_audio)控制层
 *
 * @author wmsdoc
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
     * 根据ID查询音频详情
     */
    @GetMapping("getById")
    public SaResult getById(@RequestParam("audioId") Long audioId) {
        return SaResult.data(operaAudioService.selectVOById(audioId));
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
        return SaResult.data(operaAudioService.selectByPageAndParams(pageNum, pageSize, typeId, timeFlag, filename));
    }

    @GetMapping("getByPageAndParamsByUser")
    public SaResult getByPageAndParamsByUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "typeId", defaultValue = "0", required = false) Integer typeId,
                                       @RequestParam(value = "timeFlag", defaultValue = "0", required = false) Integer timeFlag,
                                       @RequestParam(value = "filename", required = false) String filename) throws ParseException {
        return SaResult.data(operaAudioService.getByPageAndParamsByUser(pageNum, pageSize, typeId, timeFlag, filename));
    }

    /**
     * 获取音频下载排行
     */
    @GetMapping("getDownloadRank")
    public SaResult getDownloadRank(@RequestParam("time") String time) {
        return SaResult.data(operaAudioService.getDownloadRank(time));
    }

    /**
     * 获取音频评论排行
     */
    @GetMapping("getCommentRank")
    public SaResult getCommentRank(@RequestParam("time") String time) {
        return SaResult.data(operaAudioService.getCommentRank(time));
    }

    /**
     * 远程调用 新增音频
     */
    @PostMapping("insert")
    public R<Boolean> insert(@RequestParam("accountId") Long accountId, @RequestParam("filename") String filename,
                             @RequestParam("audioInfo") String audioInfo, @RequestParam("audioUrl") String audioUrl,
                             @RequestParam("typeId") Long typeId) {
        return R.ok(operaAudioService.insert(accountId, filename, audioInfo, audioUrl, typeId));
    }

    /**
     * 新增下载量
     */
    @PostMapping("addDownloadNum/{audioId}")
    public Boolean addDownloadNum(@PathVariable("audioId") Long audioId) {
        return operaAudioService.addDownloadNum(audioId);
    }

    /**
     * @Role("admin") 修改
     */
    @PutMapping("/update")
    public SaResult update(@RequestParam("audioId") Long audioId,
                           @RequestParam(value = "filename",required  = false) String filename,
                           @RequestParam(value = "audioInfo",required = false) String audioInfo,
                           @RequestParam(value = "typeId",required = false) Long typeId,
                           @RequestParam(value = "isExamine" ,required = false) Integer isExamine) {
        return SaResult.data(operaAudioService.update(audioId, filename,audioInfo,typeId, isExamine));
    }

    /**
     * 根据id删除音频
     */
    @DeleteMapping("/delete/{audioId}")
    public SaResult delete(@PathVariable Long audioId) {
        return SaResult.data(operaAudioService.removeById(audioId));
    }

    /**
     * @Role("user") 用户查询自己上传的音频
     */
    @GetMapping("getAudioByCreated")
    public SaResult getAudioByCreated(@RequestParam("pageNum")Integer pageNum,@RequestParam("pageSize")Integer pageSize){
        return SaResult.data(operaAudioService.getAudioByCreated(pageNum,pageSize));
    }
}
