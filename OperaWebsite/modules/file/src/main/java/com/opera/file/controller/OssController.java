package com.opera.file.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.file.service.OssService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OssController {

    @Resource
    private OssService ossService;

    @PostMapping("/upload")
    //请求中要携带上需要上传的文件
    public SaResult upload(@RequestParam("file") MultipartFile file, @RequestParam("fileType") Integer fileType,
                           @RequestParam(value = "operaType",required = false) Long operaType, @RequestParam(value = "textarea",required = false) String textarea) {
        return SaResult.data(ossService.upload(file, fileType, operaType, textarea));
    }

    @GetMapping("/download")
    //请求中要携带上需要上传的文件
    public void download(String objectName) {
        ossService.download(objectName);
    }
}

