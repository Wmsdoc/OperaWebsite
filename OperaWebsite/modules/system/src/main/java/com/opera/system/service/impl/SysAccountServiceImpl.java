package com.opera.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.system.domain.SysAccount;
import com.opera.system.mapper.SysAccountMapper;
import com.opera.system.service.SysAccountService;

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
}
