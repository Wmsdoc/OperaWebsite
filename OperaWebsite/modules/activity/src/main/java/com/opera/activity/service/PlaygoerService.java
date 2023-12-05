package com.opera.activity.service;

import com.opera.activity.domain.Playgoer;
import com.baomidou.mybatisplus.extension.service.IService;
public interface PlaygoerService extends IService<Playgoer>{

    /**
     * 远程调用修改用户头像
     */
    Boolean updateAvatar(Long accountId,Long playgoerId, String avatar);

    Playgoer selectById(Long playgoerId);
}
