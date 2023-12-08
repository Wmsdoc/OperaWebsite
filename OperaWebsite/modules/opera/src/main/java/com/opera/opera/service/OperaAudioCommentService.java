package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.OperaAudioComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.vo.OperaAudioCommentVO;
import com.opera.opera.domain.vo.UserAudioCommentVO;

import javax.xml.stream.events.Comment;
import java.util.List;

public interface OperaAudioCommentService extends IService<OperaAudioComment> {


    List<OperaAudioCommentVO> getCommentById(Long audioId,Integer page);

    Boolean insert(Long audioId, String content);

    Page<UserAudioCommentVO> getCommentByPlaygoerId(Long playgoerId, Integer pageNum, Integer pageSize);

    Boolean delete(Long commentId);

    Boolean update(Long commentId, String commentInfo);
}
