package com.opera.system.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.opera.system.domain.SysAccount;
import com.opera.system.domain.form.LoginBody;
import com.opera.system.service.SysAccountService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
public class SysLoginController {

    @Resource
    private SysAccountService sysAccountService;

    // 会话登录接口
    @PostMapping("/doLogin")
    public SaResult doLogin(@RequestBody LoginBody loginBody) {
        //进行数据库验证
        SysAccount account = sysAccountService.dologin(loginBody.getUsername(), loginBody.getPassword());
        if (account != null) {
            StpUtil.login(account.getAccountId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            //修改登录的id为个人资料的id
            tokenInfo.setLoginId(account.getPlaygoerId());
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("登录失败");
    }

    // 会话注销接口
    @RequestMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.data("退出成功");
    }

    // 会话校验接口
    @RequestMapping("/isLogin")
    public SaResult isLogin() {
        // 获取当前会话是否已经登录，返回true=已登录，false=未登录
        return SaResult.data(StpUtil.isLogin());
    }
}
