package com.opera.activity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.activity.domain.PlaygoerActivity;
import com.opera.activity.domain.vo.PlaygoerActivityVO;

public interface PlaygoerActivityService extends IService<PlaygoerActivity> {


    String insert(Long playgoerId, Long activityId);

    Page<PlaygoerActivityVO> getActivityByPlaygoerId(Integer pageNum, Integer pageSize);

    Boolean deletePlaygoerActivityByPlaygoerId(Long activityId, Long playgoerId);
}
