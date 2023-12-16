package com.opera.opera.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.opera.service.OperaTypeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


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

    /**
     * @Role("admin") 根据前端传入参数，进行筛选
     */
    @GetMapping("getByPageAndParams")
    public SaResult getByPageAndParams(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "typeName", required = false) String typeName) {
        return SaResult.data(operaTypeService.selectByPageAndParams(pageNum, pageSize, typeName));
    }

    /**
     * 修改
     */
    @PutMapping("/update")
    public SaResult update(@RequestParam("typeId") Long typeId, @RequestParam("typeName") String typeName) {
        return SaResult.data(operaTypeService.update(typeId, typeName));
    }

    /**
     * 删除
     */
    @DeleteMapping("/delete/{typeId}")
    public SaResult delete(@PathVariable Long typeId) {
        return SaResult.data(operaTypeService.removeById(typeId));
    }
}
