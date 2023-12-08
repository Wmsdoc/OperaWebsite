package com.opera.opera.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.api.system.RemoteSystemService;
import com.opera.opera.domain.OperaVideoComment;
import com.opera.opera.domain.vo.OperaVideoCommentVO;
import com.opera.opera.domain.vo.UserAudioCommentVO;
import com.opera.opera.domain.vo.UserVideoCommentVO;
import com.opera.opera.mapper.OperaVideoCommentMapper;
import com.opera.opera.service.OperaVideoCommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperaVideoCommentServiceImpl extends ServiceImpl<OperaVideoCommentMapper, OperaVideoComment> implements OperaVideoCommentService {

    @Resource
    private OperaVideoCommentMapper operaVideoCommentMapper;
    @Resource
    private RemoteSystemService remoteSystemService;

    @Override
    public List<OperaVideoCommentVO> getCommentById(Long videoId, Integer page) {
        return operaVideoCommentMapper.getCommentById(videoId, page);
    }

    @Override
    public Boolean insert(Long videoId, String content) {
        Long accountId = Long.valueOf((String) StpUtil.getLoginId());
        OperaVideoComment operaVideoComment = new OperaVideoComment();
        operaVideoComment.setVideoId(videoId);
        operaVideoComment.setCommentInfo(content);
        operaVideoComment.setCreatedBy(accountId);
        operaVideoComment.setPlaygoerId(remoteSystemService.getPlaygoerIdByAccountId(accountId).getData());
        return baseMapper.insert(operaVideoComment) > 0;
    }

    @Override
    public Boolean delete(Long commentId) {
        return baseMapper.deleteById(commentId) > 0;
    }

    @Override
    public Boolean update(Long commentId, String commentInfo) {
        OperaVideoComment comment = new OperaVideoComment();
        comment.setCommentId(commentId);
        comment.setCommentInfo(commentInfo);
        return baseMapper.updateById(comment) > 0;
    }

    @Override
    public Page<UserVideoCommentVO> getCommentByPlaygoerId(Long playgoerId, Integer pageNum, Integer pageSize) {
        Page<UserVideoCommentVO> page = new Page<>(pageNum, pageSize);
        return operaVideoCommentMapper.selectCommentByPlaygoerId(page, playgoerId);
    }
}
