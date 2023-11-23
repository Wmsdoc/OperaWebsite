package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opera.opera.domain.OperaVideoComment;
import com.opera.opera.domain.vo.OperaVideoCommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperaVideoCommentMapper extends BaseMapper<OperaVideoComment> {
    List<OperaVideoCommentVO> getCommentById(Long videoId);
}