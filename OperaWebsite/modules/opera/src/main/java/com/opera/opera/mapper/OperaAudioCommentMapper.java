package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opera.opera.domain.OperaAudioComment;
import com.opera.opera.domain.vo.OperaAudioCommentVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OperaAudioCommentMapper extends BaseMapper<OperaAudioComment> {
    List<OperaAudioCommentVO> selectCommentById(Long audioId);
}