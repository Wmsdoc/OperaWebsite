package com.opera.system.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
public class SysLoginController {

    // 会话登录接口
    @RequestMapping("/doLogin")
    public SaResult doLogin(String name, String pwd) {
        // 第一步：比对前端提交的账号名称、密码
        if ("zhang".equals(name) && "123456".equals(pwd)) {
            // 第1步，先登录上
            StpUtil.login(10001);
            // 第2步，获取 Token  相关参数
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            // 第3步，返回给前端
            return SaResult.data(tokenInfo);
        }
        return SaResult.error("登录失败");
    }

    // 会话注销接口
    @RequestMapping("/logout")
    public SaResult logout() {
        StpUtil.logout();
        return SaResult.ok();
    }

    // 会话校验接口
    @RequestMapping("/isLogin")
    public SaResult isLogin() {
        // 获取当前会话是否已经登录，返回true=已登录，false=未登录
        //StpUtil.isLogin();
        return SaResult.ok("当前会话登录状态：" + StpUtil.isLogin(10001));
    }
}
