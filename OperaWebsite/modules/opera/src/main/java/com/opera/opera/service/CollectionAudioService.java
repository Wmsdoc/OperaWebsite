package com.opera.opera.service;

import com.opera.opera.domain.CollectionAudio;
import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;

public interface CollectionAudioService extends IService<CollectionAudio>{


    Long countById(Long audioId);

    Boolean isCollection(Long audioId, Long playgoerId);

    Boolean insert(Long audioId, Long playgoerId);

    Boolean delete(Long audioId, Long playgoerId);
}
