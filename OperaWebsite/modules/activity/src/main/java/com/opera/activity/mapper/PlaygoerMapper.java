package com.opera.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opera.activity.domain.Playgoer;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlaygoerMapper extends BaseMapper<Playgoer> {
}