package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.domain.vo.OperaAudioVO;
import com.opera.opera.domain.vo.OperaVideoVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OperaVideoMapper extends BaseMapper<OperaVideo> {
    OperaVideoVO selectById(Long videoId);
}