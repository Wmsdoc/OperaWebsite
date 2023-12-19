package com.opera.system.service.impl;

import cn.dev33.satoken.secure.SaSecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.activity.domain.Playgoer;
import com.opera.api.activity.RemoteActivityService;
import com.opera.system.domain.SysAccount;
import com.opera.system.mapper.SysAccountMapper;
import com.opera.system.service.SysAccountService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements SysAccountService {

    @Resource
    private RemoteActivityService remoteActivityService;

    @Override
    public SysAccount dologin(String username, String password) {
        LambdaQueryWrapper<SysAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysAccount::getAccountUsername, username);
        queryWrapper.eq(SysAccount::getAccountPassword, SaSecureUtil.md5(password));
        return baseMapper.selectOne(queryWrapper);
    }

    @Override
    public Long getPlaygoerIdByAccountId(Long accountId) {
        return baseMapper.selectOne(new QueryWrapper<SysAccount>().lambda().eq(SysAccount::getAccountId, accountId))
                .getPlaygoerId();
    }

    @Override
    public Boolean updateAccountStatue(Long playgoerId) {
        LambdaUpdateWrapper<SysAccount> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SysAccount::getPlaygoerId, playgoerId);
        SysAccount account = baseMapper.selectOne(new LambdaQueryWrapper<SysAccount>().
                eq(SysAccount::getPlaygoerId, playgoerId));
        if (account.getAccountStatue() == 1)
            account.setAccountStatue(0);
        else if (account.getAccountStatue() == 0)
            account.setAccountStatue(1);
        return baseMapper.update(account, updateWrapper) > 0;
    }

    @Override
    public SysAccount register(String username, String password) {
        LambdaQueryWrapper<SysAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysAccount::getAccountUsername, username);
        SysAccount account = baseMapper.selectOne(queryWrapper);
        if (account == null) {
            //远程调用新增用户的个人信息记录
            Playgoer playgoer = remoteActivityService.insert().getData();
            if (playgoer != null) {
                //如果个人信息新增成功，创建账号
                SysAccount registerAccount = new SysAccount();
                registerAccount.setAccountUsername(username);
                registerAccount.setAccountPassword(SaSecureUtil.md5(password));
                //同步修改时间
                registerAccount.setPlaygoerId(playgoer.getPlaygoerId());
                registerAccount.setCreatedAt(playgoer.getCreatedAt());

                baseMapper.insert(registerAccount);
                LambdaQueryWrapper<SysAccount> getAccount = new LambdaQueryWrapper<>();
                getAccount.eq(SysAccount::getAccountUsername, username);
                getAccount.eq(SysAccount::getAccountPassword, SaSecureUtil.md5(password));
                registerAccount = baseMapper.selectOne(getAccount);
                return registerAccount;
            }
        }
        return null;
    }
}
