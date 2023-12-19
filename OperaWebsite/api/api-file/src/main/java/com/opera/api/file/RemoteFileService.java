package com.opera.api.opera;


import com.opera.api.opera.factory.RemoteFileFallbackFactory;
import com.opera.common.core.constant.ServiceNameConstants;
import com.opera.common.core.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(contextId = "remoteOperaService", name = ServiceNameConstants.FILE_SERVICE,
        fallbackFactory = RemoteFileFallbackFactory.class)
public interface RemoteFileService {

    @PostMapping("/audio/insert")
    public R<Boolean> audioInsert(@RequestParam("accountId") Long accountId, @RequestParam("filename") String filename,
                                  @RequestParam("audioInfo") String audioInfo, @RequestParam("audioUrl") String audioUrl,
                                  @RequestParam("typeId") Long typeId);
}
