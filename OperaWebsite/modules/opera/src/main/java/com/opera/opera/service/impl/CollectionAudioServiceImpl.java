package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.mapper.CollectionAudioMapper;
import com.opera.opera.domain.CollectionAudio;
import com.opera.opera.service.CollectionAudioService;
@Service
public class CollectionAudioServiceImpl extends ServiceImpl<CollectionAudioMapper, CollectionAudio> implements CollectionAudioService{

    @Override
    public Long countById(Long audioId) {
        QueryWrapper<CollectionAudio> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("audio_id", audioId);
        return baseMapper.selectCount(queryWrapper);
    }
}
