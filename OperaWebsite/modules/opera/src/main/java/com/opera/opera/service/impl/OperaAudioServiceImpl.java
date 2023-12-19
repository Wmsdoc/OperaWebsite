package com.opera.opera.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.common.core.util.DateUtil;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.vo.OperaAudioRankVo;
import com.opera.opera.domain.vo.OperaAudioVO;
import com.opera.opera.mapper.OperaAudioMapper;
import com.opera.opera.service.OperaAudioService;
import com.opera.opera.utils.QueryOpera;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OperaAudioServiceImpl extends ServiceImpl<OperaAudioMapper, OperaAudio> implements OperaAudioService {

    @Resource
    private OperaAudioMapper operaAudioMapper;

    @Override
    public Page<OperaAudio> selectByPageAndParams(int pageNum, int pageSize, int typeId, int timeFlag, String filename) throws ParseException {
        QueryWrapper<OperaAudio> queryWrapper = new QueryOpera<OperaAudio>().structure(typeId, timeFlag, filename);
        queryWrapper.select("audio_id", "filename", "download_url", "is_examine");
        Page<OperaAudio> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Page<OperaAudio> getByPageAndParamsByUser(Integer pageNum, Integer pageSize, Integer typeId, Integer timeFlag, String filename) throws ParseException {
        QueryWrapper<OperaAudio> queryWrapper = new QueryOpera<OperaAudio>().structure(typeId, timeFlag, filename);
        queryWrapper.select("audio_id", "filename", "download_url", "is_examine");
        queryWrapper.eq("is_examine", 1);
        Page<OperaAudio> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public OperaAudioVO selectVOById(Long audioId) {
        return operaAudioMapper.selectVOById(audioId);
    }

    @Override
    public Boolean insert(Long accountId, String filename, String audioInfo, String audioUrl, Long typeId) {
        OperaAudio operaAudio = new OperaAudio();
        operaAudio.setCreatedBy(accountId);
        operaAudio.setFilename(filename);
        operaAudio.setAudioInfo(audioInfo);
        operaAudio.setDownloadUrl(audioUrl);
        operaAudio.setTypeId(typeId);
        return baseMapper.insert(operaAudio) > 0;
    }

    @Override
    public List<OperaAudio> getDownloadRank(String time) {
        QueryWrapper<OperaAudio> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("audio_id", "filename", "download_num", "download_url", "created_at");
        //构造时间查询条件
        queryWrapper = new QueryOpera<OperaAudio>().temporalConstructs(queryWrapper, time);
        queryWrapper.orderByDesc("download_num");
        queryWrapper.last("limit 10");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<OperaAudioRankVo> getCommentRank(String time) {
        return operaAudioMapper.getCommentRank(time);
    }

    @Override
    public Boolean addDownloadNum(Long audioId) {
        OperaAudio operaAudio = baseMapper.selectById(audioId);
        operaAudio.setDownloadNum(operaAudio.getDownloadNum() + 1);
        return baseMapper.updateById(operaAudio) > 0;
    }

    @Override
    public Boolean update(Long audioId, String filename, String audioInfo, Long typeId, Integer isExamine) {
        OperaAudio operaAudio = new OperaAudio();
        operaAudio.setAudioId(audioId);
        operaAudio.setFilename(filename);
        operaAudio.setAudioInfo(audioInfo);
        operaAudio.setTypeId(typeId);
        //TODO 添加管理员权限 修改审核状态 if null -> 0 未审核
        operaAudio.setIsExamine(Objects.requireNonNullElse(isExamine, 0));
        //获取修改人 ID
        operaAudio.setUpdatedBy(StpUtil.getLoginIdAsLong());
        return baseMapper.updateById(operaAudio) > 0;
    }

    @Override
    public Page<OperaAudioVO> getAudioByCreated(Integer pageNum, Integer pageSize) {
        Long accountId = StpUtil.getLoginIdAsLong();
        Page<OperaAudio> page = new Page<>(pageNum, pageSize);
        return operaAudioMapper.getAudioByCreated(page, accountId);
    }

}
