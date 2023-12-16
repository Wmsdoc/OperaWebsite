package com.opera.activity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.activity.domain.Playgoer;
import com.opera.activity.domain.vo.PlaygoerVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlaygoerMapper extends BaseMapper<Playgoer> {
    Page<PlaygoerVO> selectByPageAndParams(Page<PlaygoerVO> page, @Param("accountUsername") String accountUsername,
                                           @Param("playgoerName") String playgoerName);
}