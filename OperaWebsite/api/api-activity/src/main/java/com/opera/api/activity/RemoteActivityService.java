package com.opera.api.activity;


import com.opera.activity.domain.Playgoer;
import com.opera.api.activity.factory.RemoteAvtivityFallbackFactory;
import com.opera.common.core.constant.ServiceNameConstants;
import com.opera.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(contextId = "remoteActivityService", name = ServiceNameConstants.ACTIVITY_SERVICE,
        fallbackFactory = RemoteAvtivityFallbackFactory.class)
public interface RemoteActivityService {

    /**
     * 远程调用修改用户头像
     */
    @GetMapping("/playgoer/updateAvatar")
    public R<Boolean> updateAvatar(@RequestParam("accountId") Long accountId, @RequestParam("playgoerId") Long playgoerId,
                                   @RequestParam("avatar") String avatar);

    /**
     * 远程调用新增用户信息
     */
    @GetMapping("/playgoer/insert")
    public R<Playgoer> insert();
}
