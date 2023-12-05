package com.opera.activity.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.activity.service.ActivityService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 活动表(activity)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping
public class ActivityController {
    /**
     * 服务对象
     */
    @Resource
    private ActivityService activityService;

    /**
     * 根据前端传入参数，进行筛选
     */
    @GetMapping("getByPageAndParams")
    public SaResult getByPageAndParams(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "activityName", required = false) String activityName,
                                       @RequestParam(value = "activityAddress", required = false) String activityAddress) {
        return SaResult.data(activityService.selectByPageAndParams(pageNum, pageSize, activityName, activityAddress));
    }

    /**
     * 根据活动id查询活动详情
     */
    @GetMapping("getById")
    public SaResult getById(@RequestParam(value = "activityId") Long activityId) {
        return SaResult.data(activityService.selectById(activityId));
    }

}
