package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.domain.vo.OperaAudioVO;
import com.opera.opera.domain.vo.OperaVideoRankVo;
import com.opera.opera.domain.vo.OperaVideoVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OperaVideoMapper extends BaseMapper<OperaVideo> {
    OperaVideoVO selectById(Long videoId);

    List<OperaVideoRankVo> getCommentRank(@Param("time") String time);

}