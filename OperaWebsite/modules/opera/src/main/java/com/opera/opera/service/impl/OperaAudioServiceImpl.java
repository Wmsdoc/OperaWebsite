package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.domain.vo.OperaAudioVO;
import com.opera.opera.mapper.OperaAudioMapper;
import com.opera.opera.service.OperaAudioService;
import com.opera.opera.utils.QueryOpera;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class OperaAudioServiceImpl extends ServiceImpl<OperaAudioMapper, OperaAudio> implements OperaAudioService {

    @Resource
    private OperaAudioMapper operaAudioMapper;

    @Override
    public Page<OperaAudio> selectByPageAndParams(int pageNum, int pageSize, int typeId, int timeFlag, String filename) throws ParseException {
        QueryWrapper<OperaAudio> queryWrapper = new QueryOpera<OperaAudio>().structure(typeId, timeFlag, filename);
        queryWrapper.select("audio_id", "filename");
        Page<OperaAudio> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public OperaAudioVO selectById(Long audioId) {
        return operaAudioMapper.selectById(audioId);
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
}
