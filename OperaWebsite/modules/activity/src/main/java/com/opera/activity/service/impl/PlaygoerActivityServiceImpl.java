package com.opera.activity.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.activity.domain.PlaygoerActivity;
import com.opera.activity.domain.vo.ActivityVO;
import com.opera.activity.domain.vo.PlaygoerActivityVO;
import com.opera.activity.mapper.PlaygoerActivityMapper;
import com.opera.activity.service.ActivityService;
import com.opera.activity.service.PlaygoerActivityService;
import com.opera.api.system.RemoteSystemService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PlaygoerActivityServiceImpl extends ServiceImpl<PlaygoerActivityMapper, PlaygoerActivity> implements PlaygoerActivityService {

    @Resource
    private RemoteSystemService remoteSystemService;
    @Resource
    private PlaygoerActivityMapper playgoerActivityMapper;
    @Resource
    private ActivityService activityService;

    /**
     * 报名活动
     *
     * @param playgoerId Long
     * @param activityId Long
     * @return string
     */
    @Override
    public String insert(Long playgoerId, Long activityId) {
        QueryWrapper<PlaygoerActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("playgoer_id", playgoerId);
        queryWrapper.eq("activity_id", activityId);
        PlaygoerActivity playgoerActivity = baseMapper.selectOne(queryWrapper);
        if (playgoerActivity != null) {
            return "已经报名过了";
        }
        // 判断人数是否已满,根据Id查询活动，获取余额
        Integer quota = activityService.quota(activityId);
        if (quota > 0) {
            PlaygoerActivity playgoerActivity1 = new PlaygoerActivity();
            playgoerActivity1.setPlaygoerId(playgoerId);
            playgoerActivity1.setActivityId(activityId);
            baseMapper.insert(playgoerActivity1);
            return "报名成功";
        }
        return "人数已满";
    }

    /**
     * 根据token获取用户参与的活动
     *
     * @param pageNum  Integer
     * @param pageSize Integer
     * @return page
     */
    @Override
    public Page<PlaygoerActivityVO> getActivityByPlaygoerId(Integer pageNum, Integer pageSize) {
        // 获取当前用户的id
        Long playgoerId = remoteSystemService.getPlaygoerIdByAccountId(StpUtil.getLoginIdAsLong()).getData();
        Page<PlaygoerActivityVO> page = new Page<>(pageNum, pageSize);
        return playgoerActivityMapper.getCollectByPlaygoerId(page, playgoerId);
    }

    @Override
    public Boolean deletePlaygoerActivityByPlaygoerId(Long activityId, Long playgoerId) {
        QueryWrapper<PlaygoerActivity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("playgoer_id", playgoerId);
        queryWrapper.eq("activity_id", activityId);
        return baseMapper.delete(queryWrapper) > 0;
    }

}
