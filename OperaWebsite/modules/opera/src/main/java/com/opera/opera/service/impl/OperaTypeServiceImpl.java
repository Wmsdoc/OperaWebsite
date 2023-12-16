package com.opera.opera.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.opera.domain.OperaType;
import com.opera.opera.domain.vo.OperaTypeVO;
import com.opera.opera.mapper.OperaTypeMapper;
import com.opera.opera.service.OperaTypeService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperaTypeServiceImpl extends ServiceImpl<OperaTypeMapper, OperaType> implements OperaTypeService {

    @Resource
    private OperaTypeMapper operaTypeMapper;

    @Override
    public List<OperaType> selectAllOperaType() {
        QueryWrapper<OperaType> queryWrapper = new QueryWrapper<>();
        queryWrapper.select( "type_id","type_name");
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public Page<OperaTypeVO> selectByPageAndParams(Integer pageNum, Integer pageSize, String typeName) {
        Page<OperaTypeVO> page = new Page<>(pageNum, pageSize);
        return operaTypeMapper.selectByPageAndParams(page, typeName);
    }

    @Override
    public Boolean update(Long typeId, String typeName) {
        OperaType operaType = new OperaType();
        operaType.setTypeId(typeId);
        operaType.setTypeName(typeName);
        return baseMapper.updateById(operaType) > 0;
    }
}
