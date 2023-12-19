package com.opera.api.file;

import cn.dev33.satoken.util.SaResult;
import com.opera.api.file.factory.RemoteFileFallbackFactory;
import com.opera.common.core.constant.ServiceNameConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(contextId = "remoteFileService", name = ServiceNameConstants.FILE_SERVICE,
        fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {
    @PostMapping("/upload")
    //请求中要携带上需要上传的文件
    public SaResult upload(@RequestParam("file") MultipartFile file, @RequestParam("fileType") Integer fileType);
}
