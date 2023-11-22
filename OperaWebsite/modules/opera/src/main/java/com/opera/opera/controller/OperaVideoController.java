package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.service.OperaVideoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
     * 根据前端传入参数，进行筛选
     */
    @GetMapping("getByPageAndParams")
    public SaResult getByPageAndParams(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "typeId", defaultValue = "0",required = false) Integer typeId,
                                       @RequestParam(value = "timeFlag",defaultValue = "0", required = false) Integer timeFlag,
                                       @RequestParam(value = "filename", required = false) String filename) throws ParseException {
        return SaResult.data(operaVideoService.selectByPageAndParams(pageNum, pageSize, typeId, timeFlag, filename));
    }

}
