package com.opera.system.controller;

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
            // 第二步：根据账号id，进行登录
            StpUtil.login(10001);
            return SaResult.ok("登录成功");
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
