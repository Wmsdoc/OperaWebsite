package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.opera.opera.domain.OperaType;
import com.opera.opera.service.OperaTypeService;
import com.opera.opera.service.impl.OperaTypeServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 戏曲类型表(opera_type)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/type")
public class OperaTypeController {
    /**
     * 服务对象
     */
    @Resource
    private OperaTypeService operaTypeService;

    /**
     * 查询所有戏曲类型
     */
    @GetMapping("/getAllOperaType")
    public SaResult getAllOperaType() {
        return SaResult.data(operaTypeService.selectAllOperaType());
    }
}
