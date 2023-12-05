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
        return SaResult.data(operaAudioService.selectById(audioId));
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
        return SaResult.data(operaAudioService.selectByPageAndParams(pageNum, pageSize, typeId, timeFlag, filename));
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
}
