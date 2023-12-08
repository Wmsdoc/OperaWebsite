package com.opera.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.activity.domain.Activity;
import com.opera.activity.domain.vo.ActivityVO;
import com.opera.activity.mapper.ActivityMapper;
import com.opera.activity.service.ActivityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity> implements ActivityService {

    @Resource
    private ActivityMapper activityMapper;

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
    public ActivityVO selectById(Long activityId) {
        return activityMapper.selectById(activityId);
    }

    @Override
    public Integer quota(Long activityId) {
        return activityMapper.quota(activityId);
    }
}
