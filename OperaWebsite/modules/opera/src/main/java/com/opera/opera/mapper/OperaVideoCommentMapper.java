package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaVideoComment;
import com.opera.opera.domain.vo.OperaVideoCommentVO;
import com.opera.opera.domain.vo.UserVideoCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperaVideoCommentMapper extends BaseMapper<OperaVideoComment> {
    List<OperaVideoCommentVO> getCommentById(@Param("videoId") Long videoId,@Param("page") Integer page);

    Page<UserVideoCommentVO> selectCommentByPlaygoerId(Page<UserVideoCommentVO> page, @Param("playgoerId") Long playgoerId);
}