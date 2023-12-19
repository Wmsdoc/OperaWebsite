package com.opera.activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.activity.domain.Playgoer;
import com.opera.activity.domain.vo.PlaygoerVO;
import com.opera.activity.mapper.PlaygoerMapper;
import com.opera.activity.service.PlaygoerService;
import com.opera.activity.util.NameUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PlaygoerServiceImpl extends ServiceImpl<PlaygoerMapper, Playgoer> implements PlaygoerService {

    @Resource
    private PlaygoerMapper playgoerMapper;

    @Override
    public Boolean updateAvatar(Long accountId, Long playgoerId, String avatar) {
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

    @Override
    public Page<PlaygoerVO> selectByPageAndParams(Integer pageNum, Integer pageSize, String accountUsername, String playgoerName) {
        Page<PlaygoerVO> page = new Page<>(pageNum, pageSize);
        return playgoerMapper.selectByPageAndParams(page, accountUsername, playgoerName);
    }

    @Override
    public Playgoer insert() {
        Playgoer playgoer = new Playgoer();
        playgoer.setPlaygoerName(NameUtil.getName());
        //获取系统当前时间
        Date date = new Date(System.currentTimeMillis());
        playgoer.setCreatedAt(date);
        baseMapper.insert(playgoer);
        return playgoer;
    }
}
