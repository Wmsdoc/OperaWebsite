package com.opera.activity.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.activity.domain.PlaygoerActivity;
import com.opera.activity.service.PlaygoerActivityService;
import com.opera.activity.service.impl.PlaygoerActivityServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


/**
 * 戏迷活动关系表(playgoer_activity)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/playgoerActivity")
public class PlaygoerActivityController {
    /**
     * 服务对象
     */
    @Resource
    private PlaygoerActivityService playgoerActivityService;

    /**
     * 新增关系表记录
     */
    @PostMapping("insert")
    public SaResult insert(@RequestParam("playgoerId") Long playgoerId, @RequestParam("activityId") Long activityId) {
        return SaResult.data(playgoerActivityService.insert(playgoerId, activityId));
    }

}
