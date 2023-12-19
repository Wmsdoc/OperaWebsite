package com.opera.system.service;

import com.opera.system.domain.SysAccount;
import com.baomidou.mybatisplus.extension.service.IService;
public interface SysAccountService extends IService<SysAccount>{


    SysAccount dologin(String username, String password);

    Long getPlaygoerIdByAccountId(Long accountId);

    Boolean updateAccountStatue(Long playgoerId);

    SysAccount register(String username, String password);
}
