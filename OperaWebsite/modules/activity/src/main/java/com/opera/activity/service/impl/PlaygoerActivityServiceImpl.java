package com.opera.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.activity.domain.PlaygoerActivity;
import com.opera.activity.mapper.PlaygoerActivityMapper;
import com.opera.activity.service.PlaygoerActivityService;
@Service
public class PlaygoerActivityServiceImpl extends ServiceImpl<PlaygoerActivityMapper, PlaygoerActivity> implements PlaygoerActivityService{

    @Override
    public String insert(Long playgoerId, Long activityId) {
        QueryWrapper<PlaygoerActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("playgoer_id",playgoerId);
        queryWrapper.eq("activity_id",activityId);
        PlaygoerActivity playgoerActivity = baseMapper.selectOne(queryWrapper);
        if (playgoerActivity != null) {
            return "已经报名过了";
        }
        PlaygoerActivity playgoerActivity1 = new PlaygoerActivity();
        playgoerActivity1.setPlaygoerId(playgoerId);
        playgoerActivity1.setActivityId(activityId);
        baseMapper.insert(playgoerActivity1);
        return "报名成功";
    }
}
