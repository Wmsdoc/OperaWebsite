package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.vo.OperaAudioRankVo;
import com.opera.opera.domain.vo.OperaAudioVO;

import java.text.ParseException;
import java.util.List;

public interface OperaAudioService extends IService<OperaAudio>{


    Page<OperaAudio> selectByPageAndParams(int pageNum,int pageSize,int typeId,int timeFlag,String filename ) throws ParseException;

    Page<OperaAudio> getByPageAndParamsByUser(Integer pageNum, Integer pageSize, Integer typeId, Integer timeFlag, String filename) throws ParseException;

    OperaAudioVO selectVOById(Long audioId);

    Boolean insert(Long accountId, String filename, String audioInfo, String audioUrl, Long typeId);

    List<OperaAudio> getDownloadRank(String time);

    List<OperaAudioRankVo> getCommentRank(String time);

    Boolean addDownloadNum(Long audioId);

    Boolean update(Long audioId, String filename, String audioInfo,Long typeId,Integer isExamine);

    Page<OperaAudioVO> getAudioByCreated(Integer pageNum,Integer pageSize);

}
