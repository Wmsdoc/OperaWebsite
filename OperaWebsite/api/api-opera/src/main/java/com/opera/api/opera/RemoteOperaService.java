package com.opera.api.opera;


import com.opera.api.opera.factory.RemoteOperaFallbackFactory;
import com.opera.common.core.constant.ServiceNameConstants;
import com.opera.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(contextId = "remoteOperaService", name = ServiceNameConstants.OPERA_SERVICE,
        fallbackFactory = RemoteOperaFallbackFactory.class)
public interface RemoteOperaService {

    @PostMapping("/audio/insert")
    public R<Boolean> audioInsert(@RequestParam("accountId") Long accountId, @RequestParam("filename") String filename,
                                  @RequestParam("audioInfo") String audioInfo, @RequestParam("audioUrl") String audioUrl,
                                  @RequestParam("typeId") Long typeId);

    /**
     * 远程调用 新增视频
     */
    @PostMapping("insert")
    public R<Boolean> videoInsert(@RequestParam("accountId") Long accountId, @RequestParam("filename") String filename,
                                  @RequestParam("videoInfo") String videoInfo, @RequestParam("videoUrl") String videoUrl,
                                  @RequestParam("typeId") Long typeId);
}
