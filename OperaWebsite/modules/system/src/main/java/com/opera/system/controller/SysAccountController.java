package com.opera.system.controller;

import com.opera.common.core.domain.R;
import com.opera.system.service.SysAccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SysAccountController {

    @Resource
    private SysAccountService sysAccountService;

    /**
     * 根据账号id获取戏迷编号
     */
    @GetMapping("/getPlaygoerIdByAccountId")
    public R<Long> getPlaygoerIdByAccountId(@RequestParam("accountId") Long accountId) {
        return R.ok(sysAccountService.getPlaygoerIdByAccountId(accountId));
    }
}
