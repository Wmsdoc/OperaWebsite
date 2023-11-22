package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.utils.QueryOpera;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.mapper.OperaVideoMapper;
import com.opera.opera.domain.OperaVideo;
import com.opera.opera.service.OperaVideoService;

@Service
public class OperaVideoServiceImpl extends ServiceImpl<OperaVideoMapper, OperaVideo> implements OperaVideoService {

    @Override
    public Page<OperaVideo> selectByPageAndParams(int pageNum, int pageSize, int typeId, int timeFlag, String filename) throws ParseException {
        QueryWrapper<OperaVideo> queryWrapper = new QueryOpera<OperaVideo>().structure(typeId, timeFlag, filename);
        Page<OperaVideo> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }

}
