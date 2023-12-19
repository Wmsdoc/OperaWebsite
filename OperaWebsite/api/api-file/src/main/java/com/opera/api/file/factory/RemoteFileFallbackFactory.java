package com.opera.api.file.factory;

import cn.dev33.satoken.util.SaResult;
import com.opera.api.file.RemoteFileService;
import com.opera.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.web.multipart.MultipartFile;

public class RemoteFileFallbackFactory implements FallbackFactory<RemoteFileService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteFileFallbackFactory.class);


    @Override
    public RemoteFileService create(Throwable cause) {
        log.error("戏曲服务调用失败:{}", cause.getMessage());
        return new RemoteFileService() {

            @Override
            public SaResult upload(MultipartFile file, Integer fileType) {
                return null;
            }
        };
    }
}
