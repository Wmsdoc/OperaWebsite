package com.opera.system.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.system.domain.Advert;
import com.opera.system.domain.Notice;
import com.opera.system.service.SystemService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SystemController {

    @Resource
    private SystemService service;

    /**
     * 网站公告管理
     */
    @GetMapping("/getNotice")
    public SaResult getNotice(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        return SaResult.data(service.getNotice(pageNum, pageSize));
    }

    /**
     * 新增公告
     */
    @PostMapping("addNotice")
    public SaResult addNotice(@RequestBody Notice notice) {
        return SaResult.data(service.addNotice(notice));
    }

    /**
     * 删除公告
     *
     * @param key uuid
     * @return boolean
     */
    @DeleteMapping("deleteNotice/{key}")
    public SaResult deleteNotice(@PathVariable String key) {
        return SaResult.data(service.deleteNotice(key));
    }

    /**
     * 新增广告
     */
    @GetMapping("getAdvert")
    public SaResult getAdvert(@RequestParam("pageNum") Integer pageNum,
                              @RequestParam("pageSize") Integer pageSize) {
        return SaResult.data(service.getAdvert(pageNum, pageSize));
    }

    /**
     * 新增广告
     */
    @PostMapping("addAdvert")
    public SaResult addAdvert(@RequestBody Advert advert) {
        return SaResult.data(service.addAdvert(advert));
    }

    /**
     * 删除广告
     *
     * @param key uuid
     * @return boolean
     */
    @DeleteMapping("deleteAdvert/{key}")
    public SaResult deleteAdvert(@PathVariable String key) {
        return SaResult.data(service.deleteAdvert(key));
    }

}
