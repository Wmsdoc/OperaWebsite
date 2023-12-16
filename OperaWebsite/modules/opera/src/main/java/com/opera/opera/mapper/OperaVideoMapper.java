package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.domain.vo.OperaAudioVO;
import com.opera.opera.domain.vo.OperaVideoRankVo;
import com.opera.opera.domain.vo.OperaVideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperaVideoMapper extends BaseMapper<OperaVideo> {

    List<OperaVideoRankVo> getCommentRank(@Param("time") String time);

    OperaVideoVO selectVOById(Long videoId);

    Page<OperaVideoVO> getVideoByCreated(Page<OperaVideo> page, @Param("accountId") Long accountId);
}