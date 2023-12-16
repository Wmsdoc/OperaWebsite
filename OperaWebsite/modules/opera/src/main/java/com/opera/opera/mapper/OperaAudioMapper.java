package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.vo.OperaAudioRankVo;
import com.opera.opera.domain.vo.OperaAudioVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperaAudioMapper extends BaseMapper<OperaAudio> {
    OperaAudioVO selectVOById(Long audioId);

    List<OperaAudioRankVo> getCommentRank(@Param("time") String time);

    Page<OperaAudioVO> getAudioByCreated(Page<OperaAudio> page, @Param("accountId") Long accountId);
}