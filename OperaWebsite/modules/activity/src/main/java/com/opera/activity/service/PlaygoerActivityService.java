package com.opera.activity.service;

import com.opera.activity.domain.PlaygoerActivity;
import com.baomidou.mybatisplus.extension.service.IService;
public interface PlaygoerActivityService extends IService<PlaygoerActivity>{


    String insert(Long playgoerId, Long activityId);
}
