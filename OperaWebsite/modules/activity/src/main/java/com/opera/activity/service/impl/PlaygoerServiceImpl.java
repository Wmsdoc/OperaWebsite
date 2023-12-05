package com.opera.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.activity.domain.Playgoer;
import com.opera.activity.mapper.PlaygoerMapper;
import com.opera.activity.service.PlaygoerService;
import org.springframework.stereotype.Service;

@Service
public class PlaygoerServiceImpl extends ServiceImpl<PlaygoerMapper, Playgoer> implements PlaygoerService {

    @Override
    public Boolean updateAvatar(Long accountId, Long playgoerId, String avatar) {
        LambdaQueryWrapper<Playgoer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Playgoer::getPlaygoerId, playgoerId);
        Playgoer playgoer = new Playgoer();
        playgoer.setPlaygoerId(playgoerId);
        playgoer.setPlaygoerAvatar(avatar);
        playgoer.setUpdatedBy(accountId);
        return baseMapper.updateById(playgoer) > 0;
    }

    @Override
    public Playgoer selectById(Long playgoerId) {
        LambdaQueryWrapper<Playgoer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Playgoer::getPlaygoerId, playgoerId);
        return baseMapper.selectOne(queryWrapper);
    }
}
