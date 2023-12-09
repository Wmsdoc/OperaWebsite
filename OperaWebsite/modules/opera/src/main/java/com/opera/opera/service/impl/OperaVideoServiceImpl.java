package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.domain.vo.OperaVideoRankVo;
import com.opera.opera.domain.vo.OperaVideoVO;
import com.opera.opera.mapper.OperaVideoMapper;
import com.opera.opera.service.OperaVideoService;
import com.opera.opera.utils.QueryOpera;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class OperaVideoServiceImpl extends ServiceImpl<OperaVideoMapper, OperaVideo> implements OperaVideoService {

    @Resource
    private OperaVideoMapper operaVideoMapper;

    @Override
    public Page<OperaVideo> selectByPageAndParams(int pageNum, int pageSize, int typeId, int timeFlag, String filename) throws ParseException {
        QueryWrapper<OperaVideo> queryWrapper = new QueryOpera<OperaVideo>().structure(typeId, timeFlag, filename);
        queryWrapper.select("video_id", "filename", "download_url");
        Page<OperaVideo> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public OperaVideoVO selectById(Long videoId) {
        return operaVideoMapper.selectById(videoId);
    }

    @Override
    public Boolean insert(Long accountId, String filename, String videoInfo, String videoUrl, Long typeId) {
        OperaVideo operaVideo = new OperaVideo();
        operaVideo.setCreatedBy(accountId);
        operaVideo.setFilename(filename);
        operaVideo.setVideoInfo(videoInfo);
        operaVideo.setDownloadUrl(videoUrl);
        operaVideo.setTypeId(typeId);
        return baseMapper.insert(operaVideo) > 0;
    }

    @Override
    public List<OperaVideo> getDownloadRank(String time) {
        QueryWrapper<OperaVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("video_id", "filename", "download_num", "download_url", "updated_at");
        //构造时间查询条件
        queryWrapper = new QueryOpera<OperaVideo>().temporalConstructs(queryWrapper, time);
        queryWrapper.orderByDesc("download_num");
        queryWrapper.last("limit 10");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<OperaVideoRankVo> getCommentRank(String time) {
        return operaVideoMapper.getCommentRank(time);
    }

    @Override
    public Boolean addDownloadNum(Long videoId) {
        OperaVideo operaVideo = baseMapper.selectById(videoId);
        operaVideo.setDownloadNum(operaVideo.getDownloadNum() + 1);
        return baseMapper.updateById(operaVideo) > 0;
    }
}
