package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.OperaAudio;

import java.text.ParseException;

public interface OperaAudioService extends IService<OperaAudio>{


    Page<OperaAudio> selectByPageAndParams(int pageNum,int pageSize,int timeFlag,OperaAudio operaAudio) throws ParseException;
}
