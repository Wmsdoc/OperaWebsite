package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.vo.OperaAudioVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperaAudioMapper extends BaseMapper<OperaAudio> {
    OperaAudioVO selectById(Long audioId);
}