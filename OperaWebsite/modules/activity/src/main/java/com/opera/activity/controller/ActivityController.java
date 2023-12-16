package com.opera.activity.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.activity.domain.Activity;
import com.opera.activity.service.ActivityService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


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
        return SaResult.data(activityService.selectVOById(activityId));
    }

    /**
     * @Role("admin") 根据活动id查询活动详情
     */
    @GetMapping("getActivityById")
    public SaResult getActivityById(@RequestParam(value = "activityId") Long activityId) {
        return SaResult.data(activityService.getById(activityId));
    }

    /**
     * 修改活动
     */
    @PostMapping("insert")
    public SaResult insert(@RequestBody Activity activity) {
        return SaResult.data(activityService.insert(activity));
    }

    /**
     * 修改活动
     */
    @PutMapping("update")
    public SaResult update(@RequestBody Activity activity) {
        return SaResult.data(activityService.update(activity));
    }

    /**
     * 删除
     */
    @DeleteMapping("delete/{activityId}")
    public SaResult delete(@PathVariable String activityId) {
        return SaResult.data(activityService.removeById(activityId));
    }

}
