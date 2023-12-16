package com.opera.activity.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.activity.domain.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.activity.domain.vo.ActivityVO;

import java.util.List;

public interface ActivityService extends IService<Activity>{


    Page<Activity> selectByPageAndParams(Integer pageNum, Integer pageSize, String activityName, String activityAddress);

    ActivityVO selectVOById(Long activityId);

    /**
     * 根据 id 统计剩余名额
     */
    Integer quota(Long activityId);

    Boolean insert(Activity activity);

    Integer update(Activity activity);
}
