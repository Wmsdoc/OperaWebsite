package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.OperaAudioComment;
import com.opera.opera.domain.vo.OperaAudioCommentVO;
import com.opera.opera.domain.vo.UserAudioCommentVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperaAudioCommentMapper extends BaseMapper<OperaAudioComment> {
    List<OperaAudioCommentVO> selectCommentById(@Param("audioId") Long audioId,@Param("page") Integer page);

    Page<UserAudioCommentVO> selectCommentByPlaygoerId(Page<UserAudioCommentVO> page,@Param("playgoerId") Long playgoerId);

    Page<OperaAudioCommentVO> selectByPageAndParams(Page<OperaAudioCommentVO> page,@Param("filename") String filename,
                                                    @Param("playgoerName") String playgoerName,@Param("typeId") Long typeId);
}