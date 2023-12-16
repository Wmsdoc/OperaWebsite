package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.OperaType;
import com.opera.opera.domain.vo.OperaTypeVO;

import java.util.List;

public interface OperaTypeService extends IService<OperaType>{

    /**
     * 查询所有戏曲类型
     * @return list
     */
    List<OperaType> selectAllOperaType();

    Page<OperaTypeVO> selectByPageAndParams(Integer pageNum, Integer pageSize, String typeName);

    Boolean update(Long typeId, String typeName);
}
