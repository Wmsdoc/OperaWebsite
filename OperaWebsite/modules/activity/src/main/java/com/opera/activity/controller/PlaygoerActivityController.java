package com.opera.activity.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.activity.service.PlaygoerActivityService;
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

    /**
     * 根据token获取用户参与的活动
     */
    @PostMapping("/getActivityByPlaygoerId/{pageNum}/{pageSize}")
    public SaResult getCollectByPlaygoerId(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        return SaResult.data(playgoerActivityService.getActivityByPlaygoerId(pageNum, pageSize));
    }

    /**
     * 取消用户收藏
     */
    @DeleteMapping("/delete")
    public SaResult deletePlaygoerActivity(@RequestParam("activityId") Long activityId,
                                           @RequestParam("playgoerId") Long playgoerId) {
        return SaResult.data(playgoerActivityService.deletePlaygoerActivityByPlaygoerId(activityId, playgoerId));
    }
}
