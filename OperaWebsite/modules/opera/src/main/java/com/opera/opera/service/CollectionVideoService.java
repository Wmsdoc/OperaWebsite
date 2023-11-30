package com.opera.opera.service;

import com.opera.opera.domain.CollectionVideo;
import com.baomidou.mybatisplus.extension.service.IService;
public interface CollectionVideoService extends IService<CollectionVideo>{


    Long countById(Long videoId);

    Boolean isCollection(Long videoId, Long playgoerId);

    Boolean insert(Long videoId, Long playgoerId);

    Boolean delete(Long videoId, Long playgoerId);
}
