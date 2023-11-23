package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.OperaVideo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.vo.OperaAudioVO;
import com.opera.opera.domain.vo.OperaVideoVO;

import java.text.ParseException;

public interface OperaVideoService extends IService<OperaVideo>{


    Page<OperaVideo> selectByPageAndParams(int pageNum,int pageSize,int typeId,int timeFlag,String filename ) throws ParseException;

    OperaVideoVO selectById(Long videoId);
}
