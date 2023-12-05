package com.opera.api.system.factory;

import com.opera.common.core.domain.R;
import com.opera.api.system.RemoteSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class RemoteSystemFallbackFactory implements FallbackFactory<RemoteSystemService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteSystemFallbackFactory.class);

    @Override
    public RemoteSystemService create(Throwable throwable) {
        log.error("系统服务调用失败:{}", throwable.getMessage());
        return new RemoteSystemService() {
            @Override
            public R<Long> getPlaygoerIdByAccountId(Long accountId) {
                return R.fail("预约服务查询失败：" + throwable.getMessage());
            }
        };
    }
}
