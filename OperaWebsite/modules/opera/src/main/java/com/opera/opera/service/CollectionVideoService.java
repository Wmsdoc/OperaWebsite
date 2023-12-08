package com.opera.opera.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.opera.opera.domain.CollectionVideo;
import com.opera.opera.domain.vo.CollectionVideoVO;

import java.util.List;

public interface CollectionVideoService extends IService<CollectionVideo>{


    Long countById(Long videoId);

    Boolean isCollection(Long videoId, Long playgoerId);

    Boolean insert(Long videoId, Long playgoerId);

    Boolean delete(Long videoId, Long playgoerId);

    Page<CollectionVideoVO> getCollectByPlaygoerId(Integer pageNum, Integer pageSize);
}
