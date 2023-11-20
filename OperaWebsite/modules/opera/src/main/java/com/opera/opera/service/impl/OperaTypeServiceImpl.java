package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaType;
import com.opera.opera.mapper.OperaTypeMapper;
import com.opera.opera.service.OperaTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperaTypeServiceImpl extends ServiceImpl<OperaTypeMapper, OperaType> implements OperaTypeService {

    @Override
    public List<OperaType> selectAllOperaType() {
        QueryWrapper<OperaType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select( "type_id","type_name");
        return baseMapper.selectList(queryWrapper);
    }
}
