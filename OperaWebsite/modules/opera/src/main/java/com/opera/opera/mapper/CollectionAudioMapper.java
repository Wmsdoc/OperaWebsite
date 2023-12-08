package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.CollectionAudio;
import com.opera.opera.domain.vo.CollectionAudioVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CollectionAudioMapper extends BaseMapper<CollectionAudio> {
    Page<CollectionAudioVO> getCollectByPlaygoerId(Page<CollectionAudioVO> page, @Param("playgoerId") Long playgoerId);
}