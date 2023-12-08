package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.CollectionVideo;
import com.opera.opera.domain.vo.CollectionAudioVO;
import com.opera.opera.domain.vo.CollectionVideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectionVideoMapper extends BaseMapper<CollectionVideo> {
    Page<CollectionVideoVO> getCollectByPlaygoerId(Page<CollectionVideoVO> page,@Param("playgoerId") Long playgoerId);
}