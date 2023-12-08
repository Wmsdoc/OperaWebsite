package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaVideoComment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.vo.OperaVideoCommentVO;
import com.opera.opera.domain.vo.UserVideoCommentVO;

import java.util.List;

public interface OperaVideoCommentService extends IService<OperaVideoComment>{


    List<OperaVideoCommentVO> getCommentById(Long videoId,Integer page);

    Boolean insert(Long videoId, String content);

    Boolean delete(Long commentId);

    Boolean update(Long commentId, String commentInfo);

    Page<UserVideoCommentVO> getCommentByPlaygoerId(Long playgoerId, Integer pageNum, Integer pageSize);
}
