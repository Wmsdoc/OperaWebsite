package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.OperaType;

import java.util.List;

public interface OperaTypeService extends IService<OperaType>{

    /**
     * 查询所有戏曲类型
     * @return list
     */
    List<OperaType> selectAllOperaType();
}
