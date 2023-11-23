package com.opera.opera.service;

import com.opera.opera.domain.CollectionVideo;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CollectionVideoService extends IService<CollectionVideo>{


    Long countById(Long videoId);
}
