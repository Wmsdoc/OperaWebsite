package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaAudio;
import com.opera.opera.mapper.OperaAudioMapper;
import com.opera.opera.service.OperaAudioService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class OperaAudioServiceImpl extends ServiceImpl<OperaAudioMapper, OperaAudio> implements OperaAudioService {

    @Override
    public Page<OperaAudio> selectByPageAndParams(int pageNum, int pageSize, int timeFlag, OperaAudio operaAudio) throws ParseException {
        QueryWrapper<OperaAudio> queryWrapper = new QueryWrapper<>();
        if (operaAudio.getFilename() != null) {
            queryWrapper.like("filename", operaAudio.getFilename());
        }
        if (operaAudio.getTypeId() != 0) {
            queryWrapper.like("type_id", operaAudio.getTypeId());
        }
        if (timeFlag != 0) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String stateTime = "2023-01-01";
            String endTime = "2023-01-01";
            switch (timeFlag) {
                case 1:
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    break;
                case 2:
                    endTime= "2022-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 3:
                    stateTime = "2022-01-01";
                    endTime= "2021-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 4:
                    stateTime = "2021-01-01";
                    endTime= "2020-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 5:
                    stateTime = "2020-01-01";
                    endTime= "2016-01-01";
                    queryWrapper.ge("created_at", sdf.parse(endTime));
                    queryWrapper.lt("created_at", sdf.parse(stateTime));
                    break;
                case 6:
                    endTime= "2016-01-01";
                    queryWrapper.lt("created_at", sdf.parse(endTime));
            }
        }
        Page<OperaAudio> page = new Page<>(pageNum, pageSize);
        return baseMapper.selectPage(page, queryWrapper);
    }
}
