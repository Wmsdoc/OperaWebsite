package com.opera.opera.service;

import com.opera.opera.domain.OperaAudioComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.vo.OperaAudioCommentVO;

import java.util.List;

public interface OperaAudioCommentService extends IService<OperaAudioComment> {


    List<OperaAudioCommentVO> getCommentById(Long audioId);
}
