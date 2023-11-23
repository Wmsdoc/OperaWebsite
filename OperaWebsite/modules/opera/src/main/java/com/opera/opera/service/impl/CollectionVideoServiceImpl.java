package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.CollectionVideo;
import com.opera.opera.mapper.CollectionVideoMapper;
import com.opera.opera.service.CollectionVideoService;

@Service
public class CollectionVideoServiceImpl extends ServiceImpl<CollectionVideoMapper, CollectionVideo> implements CollectionVideoService {

    @Override
    public Long countById(Long videoId) {
        QueryWrapper<CollectionVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id", videoId);
        return baseMapper.selectCount(queryWrapper);
    }
}
