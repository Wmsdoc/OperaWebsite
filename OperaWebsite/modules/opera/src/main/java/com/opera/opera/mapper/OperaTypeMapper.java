package com.opera.opera.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.opera.domain.OperaType;
import com.opera.opera.domain.vo.OperaTypeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OperaTypeMapper extends BaseMapper<OperaType> {
    Page<OperaTypeVO> selectByPageAndParams(Page<OperaTypeVO> page, @Param("typeName") String typeName);
}