package com.opera.api.activity.factory;

import com.opera.api.activity.RemoteActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;

public class RemoteAvtivityFallbackFactory implements FallbackFactory<RemoteActivityService> {
    private static final Logger log = LoggerFactory.getLogger(RemoteAvtivityFallbackFactory.class);

    @Override
    public RemoteActivityService create(Throwable cause) {
        return null;
    }
}
