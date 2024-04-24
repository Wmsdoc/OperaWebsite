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
    @PostMapping("/login")
    public SaResult doLogin(@RequestBody LoginBody loginBody) {
        //进行数据库验证
        SysAccount account = sysAccountService.dologin(loginBody.getUsername(), loginBody.getPassword());
        if (account != null) {
            StpUtil.login(account.getAccountId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            //修改登录的id为个人资料的id,这里是playgoerId,防止前端直接使用accountId
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
        boolean isLogin = StpUtil.isLogin();
        if (isLogin) {
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return SaResult.data(tokenInfo);
        }
        return SaResult.data(false);
    }

    //注册
    @RequestMapping("/register")
    public SaResult register(@RequestBody LoginBody loginBody) {
        SysAccount account = sysAccountService.register(loginBody.getUsername(), loginBody.getPassword());
        if (account != null) {
            StpUtil.login(account.getAccountId());
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            //修改登录的id为个人资料的id
            tokenInfo.setLoginId(account.getPlaygoerId());
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("注册失败,用户名存在");
    }
}
