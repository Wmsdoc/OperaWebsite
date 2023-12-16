package com.opera.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.system.domain.SysAccount;
import com.opera.system.mapper.SysAccountMapper;
import com.opera.system.service.SysAccountService;
import org.springframework.stereotype.Service;

@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements SysAccountService {

    @Override
    public SysAccount dologin(String username, String password) {
        LambdaQueryWrapper<SysAccount> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysAccount::getAccountUsername, username);
        queryWrapper.eq(SysAccount::getAccountPassword, password);
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
}
