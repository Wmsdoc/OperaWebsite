package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.domain.vo.OperaVideoRankVo;
import com.opera.opera.domain.vo.OperaVideoVO;

import java.text.ParseException;
import java.util.List;

public interface OperaVideoService extends IService<OperaVideo>{


    Page<OperaVideo> selectByPageAndParams(int pageNum,int pageSize,int typeId,int timeFlag,String filename ) throws ParseException;

    OperaVideoVO selectVOById(Long videoId);

    Boolean insert(Long accountId, String filename, String videoInfo, String videoUrl, Long typeId);

    List<OperaVideo> getDownloadRank(String time);

    List<OperaVideoRankVo> getCommentRank(String time);

    Boolean addDownloadNum(Long videoId);

    Boolean update(Long videoId, String filename, String videoInfo,Long typeId, Integer isExamine);

    Page<OperaVideoVO> getVideoByCreated(Integer pageNum, Integer pageSize);
}
