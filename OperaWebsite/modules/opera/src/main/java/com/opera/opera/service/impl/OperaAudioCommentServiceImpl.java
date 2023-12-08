package com.opera.opera.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.api.system.RemoteSystemService;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.OperaAudioComment;
import com.opera.opera.domain.vo.OperaAudioCommentVO;
import com.opera.opera.domain.vo.UserAudioCommentVO;
import com.opera.opera.mapper.OperaAudioCommentMapper;
import com.opera.opera.service.OperaAudioCommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OperaAudioCommentServiceImpl extends ServiceImpl<OperaAudioCommentMapper, OperaAudioComment> implements OperaAudioCommentService {

    @Resource
    private OperaAudioCommentMapper operaAudioCommentMapper;
    @Resource
    private RemoteSystemService remoteSystemService;

    @Override
    public List<OperaAudioCommentVO> getCommentById(Long audioId, Integer page) {
        return operaAudioCommentMapper.selectCommentById(audioId, page);
    }

    @Override
    @Transactional
    public Boolean insert(Long audioId, String content) {
        Long accountId = Long.valueOf((String) StpUtil.getLoginId());
        OperaAudioComment operaAudioComment = new OperaAudioComment();
        operaAudioComment.setAudioId(audioId);
        operaAudioComment.setCommentInfo(content);
        operaAudioComment.setCreatedBy(accountId);
        operaAudioComment.setPlaygoerId(remoteSystemService.getPlaygoerIdByAccountId(accountId).getData());
        return baseMapper.insert(operaAudioComment) > 0;
    }

    @Override
    public Page<UserAudioCommentVO> getCommentByPlaygoerId(Long playgoerId, Integer pageNum, Integer pageSize) {
        Page<UserAudioCommentVO> page = new Page<>(pageNum, pageSize);
        return operaAudioCommentMapper.selectCommentByPlaygoerId(page, playgoerId);
    }

    @Override
    public Boolean delete(Long commentId) {
        return baseMapper.deleteById(commentId) > 0;
    }

    @Override
    public Boolean update(Long commentId, String commentInfo) {
        OperaAudioComment comment = new OperaAudioComment();
        comment.setCommentId(commentId);
        comment.setCommentInfo(commentInfo);
        return baseMapper.updateById(comment) > 0;
    }
}
