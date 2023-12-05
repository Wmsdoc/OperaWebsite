package com.opera.api.system;

import com.opera.api.system.factory.RemoteSystemFallbackFactory;
import com.opera.common.core.constant.ServiceNameConstants;
import com.opera.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(contextId = "remoteSystemService", name = ServiceNameConstants.SYSTEM_SERVICE,
        fallbackFactory = RemoteSystemFallbackFactory.class)
public interface RemoteSystemService {

    /**
     * 根据账号id获取戏迷编号
     */
    @GetMapping("/getPlaygoerIdByAccountId")
    public R<Long> getPlaygoerIdByAccountId(@RequestParam("accountId") Long accountId);
}
