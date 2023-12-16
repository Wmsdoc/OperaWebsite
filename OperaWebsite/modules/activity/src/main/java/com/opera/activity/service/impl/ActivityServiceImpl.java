package com.opera.activity.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.activity.domain.Activity;
import com.opera.activity.domain.PlaygoerActivity;
import com.opera.activity.domain.vo.ActivityVO;
import com.opera.activity.mapper.ActivityMapper;
import com.opera.activity.mapper.PlaygoerActivityMapper;
import com.opera.activity.service.ActivityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private PlaygoerActivityMapper playgoerActivityMapper;

    @Override
    public Page<Activity> selectByPageAndParams(Integer pageNum, Integer pageSize, String activityName, String activityAddress) {
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        if (activityName != null) {
            queryWrapper.like("activity_name", activityName);
        }
        if (activityAddress != null) {
            queryWrapper.like("activity_address", activityAddress);
        }
        queryWrapper.select("activity_id", "activity_name", "activity_address", "start_time");
        Page<Activity> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public ActivityVO selectVOById(Long activityId) {
        return activityMapper.selectVOById(activityId);
    }

    @Override
    public Integer quota(Long activityId) {
        return activityMapper.quota(activityId);
    }

    @Override
    public Boolean insert(Activity activity) {
        activity.setCreatedBy(StpUtil.getLoginIdAsLong());
        return activityMapper.insert(activity) > 0;
    }

    @Override
    public Integer update(Activity activity) {
        // 统计已报名的人数
        LambdaQueryWrapper<PlaygoerActivity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PlaygoerActivity::getActivityId, activity.getActivityId());
        long count = playgoerActivityMapper.selectCount(queryWrapper);
        if (activity.getActivityNum() < count) {
            return -1;
        }
        activity.setUpdatedBy(StpUtil.getLoginIdAsLong());
        boolean result = activityMapper.updateById(activity) > 0;
        if (result)
            return 1;
        else
            return 0;
    }
}
