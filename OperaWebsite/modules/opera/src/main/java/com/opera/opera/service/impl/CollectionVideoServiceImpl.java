package com.opera.opera.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.api.system.RemoteSystemService;
import com.opera.opera.domain.vo.CollectionAudioVO;
import com.opera.opera.domain.vo.CollectionVideoVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.CollectionVideo;
import com.opera.opera.mapper.CollectionVideoMapper;
import com.opera.opera.service.CollectionVideoService;

@Service
public class CollectionVideoServiceImpl extends ServiceImpl<CollectionVideoMapper, CollectionVideo> implements CollectionVideoService {

    @Resource
    private CollectionVideoMapper collectionVideoMapper;
    @Resource
    private RemoteSystemService remoteSystemService;

    @Override
    public Long countById(Long videoId) {
        QueryWrapper<CollectionVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id", videoId);
        return baseMapper.selectCount(queryWrapper);
    }

    @Override
    public Boolean isCollection(Long videoId, Long playgoerId) {
        QueryWrapper<CollectionVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id", videoId);
        queryWrapper.eq("playgoer_id", playgoerId);
        return baseMapper.selectCount(queryWrapper) > 0;
    }

    @Override
    public Boolean insert(Long videoId, Long playgoerId) {
        CollectionVideo collectionVideo = new CollectionVideo();
        collectionVideo.setVideoId(videoId);
        collectionVideo.setPlaygoerId(playgoerId);
        return baseMapper.insert(collectionVideo) > 0;
    }

    @Override
    public Boolean delete(Long videoId, Long playgoerId) {
        QueryWrapper<CollectionVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_id", videoId);
        queryWrapper.eq("playgoer_id", playgoerId);
        return baseMapper.delete(queryWrapper) > 0;
    }

    @Override
    public Page<CollectionVideoVO> getCollectByPlaygoerId(Integer pageNum, Integer pageSize) {
        // 获取当前用户id
        Long playgoerId = remoteSystemService.getPlaygoerIdByAccountId(StpUtil.getLoginIdAsLong()).getData();
        Page<CollectionVideoVO> page = new Page<>(pageNum, pageSize);
        return collectionVideoMapper.getCollectByPlaygoerId(page, playgoerId);
    }
}
