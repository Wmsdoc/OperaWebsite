package com.opera.system.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.opera.system.domain.SysAccount;
import com.opera.system.mapper.SysAccountMapper;
import com.opera.system.service.SysAccountService;
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements SysAccountService{

}
