package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.opera.opera.domain.vo.OperaAudioCommentVO;
import com.opera.opera.utils.QueryOpera;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaAudioComment;
import com.opera.opera.mapper.OperaAudioCommentMapper;
import com.opera.opera.service.OperaAudioCommentService;

@Service
public class OperaAudioCommentServiceImpl extends ServiceImpl<OperaAudioCommentMapper, OperaAudioComment> implements OperaAudioCommentService {

    @Resource
    private OperaAudioCommentMapper operaAudioCommentMapper;

    @Override
    public List<OperaAudioCommentVO> getCommentById(Long audioId) {
        return operaAudioCommentMapper.selectCommentById(audioId);
    }
}
