package com.opera.system.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.common.core.domain.R;
import com.opera.system.service.SysAccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 通过 playgoerId 封禁用户
     */
    @PutMapping("/updateAccountStatue/{playgoerId}")
    public SaResult banAccount(@PathVariable Long playgoerId) {
        return SaResult.data(sysAccountService.updateAccountStatue(playgoerId));
    }
}
