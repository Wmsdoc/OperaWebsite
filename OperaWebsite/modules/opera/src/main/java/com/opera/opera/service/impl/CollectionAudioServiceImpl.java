package com.opera.opera.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.mapper.CollectionAudioMapper;
import com.opera.opera.domain.CollectionAudio;
import com.opera.opera.service.CollectionAudioService;

@Service
public class CollectionAudioServiceImpl extends ServiceImpl<CollectionAudioMapper, CollectionAudio> implements CollectionAudioService {

    @Override
    public Long countById(Long audioId) {
        QueryWrapper<CollectionAudio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audio_id", audioId);
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Boolean isCollection(Long audioId, Long playgoerId) {
        QueryWrapper<CollectionAudio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audio_id", audioId);
        queryWrapper.eq("playgoer_id", playgoerId);
        return baseMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public Boolean insert(Long audioId, Long playgoerId) {
        CollectionAudio collectionAudio = new CollectionAudio();
        collectionAudio.setAudioId(audioId);
        collectionAudio.setPlaygoerId(playgoerId);
        return baseMapper.insert(collectionAudio) > 0;
    }

    @Override
    public Boolean delete(Long audioId, Long playgoerId) {
        QueryWrapper<CollectionAudio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audio_id", audioId);
        queryWrapper.eq("playgoer_id", playgoerId);
        return baseMapper.delete(queryWrapper) > 0;
    }


}
