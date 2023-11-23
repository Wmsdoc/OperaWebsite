package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.domain.vo.OperaVideoVO;
import com.opera.opera.mapper.OperaVideoMapper;
import com.opera.opera.service.OperaVideoService;
import com.opera.opera.utils.QueryOpera;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class OperaVideoServiceImpl extends ServiceImpl<OperaVideoMapper, OperaVideo> implements OperaVideoService {

    @Resource
    private OperaVideoMapper operaVideoMapper;

    @Override
    public Page<OperaVideo> selectByPageAndParams(int pageNum, int pageSize, int typeId, int timeFlag, String filename) throws ParseException {
        QueryWrapper<OperaVideo> queryWrapper = new QueryOpera<OperaVideo>().structure(typeId, timeFlag, filename);
        queryWrapper.select("video_id", "filename");
        Page<OperaVideo> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

    @Override
    public OperaVideoVO selectById(Long videoId) {
        return operaVideoMapper.selectById(videoId);
    }
}
