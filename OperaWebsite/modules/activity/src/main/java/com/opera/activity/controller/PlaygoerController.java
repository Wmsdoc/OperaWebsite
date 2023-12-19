package com.opera.activity.controller;

import cn.dev33.satoken.util.SaResult;
import com.opera.activity.domain.Playgoer;
import com.opera.activity.service.PlaygoerService;
import com.opera.common.core.domain.R;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;


/**
 * 戏迷个人信息表(playgoer)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/playgoer")
public class PlaygoerController {
    /**
     * 服务对象
     */
    @Resource
    private PlaygoerService playgoerService;

    /**
     * @Role("admin")
     * 根据前端传入参数，进行筛选
     */
    @GetMapping("/getByPageAndParams")
    public SaResult getByPageAndParams(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                       @RequestParam(value = "accountUsername", required = false) String accountUsername,
                                       @RequestParam(value = "playgoerName", required = false) String playgoerName)  {
        return SaResult.data(playgoerService.selectByPageAndParams(pageNum, pageSize, accountUsername, playgoerName));
    }

    /**
     * 远程调用修改用户头像
     */
    @GetMapping("/updateAvatar")
    public R<Boolean> updateAvatar(@RequestParam("accountId") Long accountId, @RequestParam("playgoerId") Long playgoerId,
                                   @RequestParam("avatar") String avatar) {
        return R.ok(playgoerService.updateAvatar(accountId, playgoerId, avatar));
    }

    /**
     * 远程调用新增用户信息
     */
    @GetMapping("/insert")
    public R<Playgoer> insert(){
        return R.ok(playgoerService.insert());
    }

    /**
     * 根据Id查询用户信息
     */
    @PostMapping("/getById/{playgoerId}")
    public SaResult getById(@PathVariable Long playgoerId) {
        return SaResult.data(playgoerService.selectById(playgoerId));
    }

    /**
     * 根据Id修改用户信息
     */
    @PutMapping("/updateById")
    public SaResult updateById(@RequestBody Playgoer playgoer){
        return SaResult.data(playgoerService.updateById(playgoer));
    }

}
