package com.opera.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.activity.domain.PlaygoerActivity;
import com.opera.activity.domain.vo.PlaygoerActivityVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlaygoerActivityMapper extends BaseMapper<PlaygoerActivity> {
    Page<PlaygoerActivityVO> getCollectByPlaygoerId(Page<PlaygoerActivityVO> page,@Param("playgoerId") Long playgoerId);
}