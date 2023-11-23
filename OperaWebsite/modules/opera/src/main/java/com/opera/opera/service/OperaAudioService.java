package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.vo.OperaAudioVO;

import java.text.ParseException;

public interface OperaAudioService extends IService<OperaAudio>{


    Page<OperaAudio> selectByPageAndParams(int pageNum,int pageSize,int typeId,int timeFlag,String filename ) throws ParseException;

    OperaAudioVO selectById(Long audioId);
}
