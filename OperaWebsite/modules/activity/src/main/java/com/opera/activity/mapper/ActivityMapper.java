package com.opera.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.activity.domain.Activity;
import com.opera.activity.domain.vo.ActivityVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    ActivityVO selectById(Long activityId);

    Integer quota(Long activityId);

}