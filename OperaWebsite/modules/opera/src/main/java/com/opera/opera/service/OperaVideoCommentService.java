package com.opera.opera.service;

import com.opera.opera.domain.OperaVideoComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.vo.OperaVideoCommentVO;

import java.util.List;

public interface OperaVideoCommentService extends IService<OperaVideoComment>{


    List<OperaVideoCommentVO> getCommentById(Long videoId,Integer page);

    Boolean insert(Long videoId, String content);
}
