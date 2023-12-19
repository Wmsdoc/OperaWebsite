package com.opera.api.file.factory;

import com.opera.api.file.RemoteOperaService;
import com.opera.common.core.domain.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class RemoteOperaFallbackFactory implements FallbackFactory<RemoteOperaService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteOperaFallbackFactory.class);


    @Override
    public RemoteOperaService create(Throwable cause) {
        log.error("戏曲服务调用失败:{}", cause.getMessage());
        return new RemoteOperaService() {

            @Override
            public R<Boolean> audioInsert(Long accountId, String filename, String audioInfo, String audioUrl, Long typeId) {
                return R.fail("新增音频失败" + cause.getMessage());
            }

            @Override
            public R<Boolean> videoInsert(Long accountId, String filename, String videoInfo, String videoUrl, Long typeId) {
                return R.fail("新增视频失败" + cause.getMessage());
            }
        };
    }
}
