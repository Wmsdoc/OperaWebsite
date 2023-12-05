package com.opera.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    Boolean upload(MultipartFile file, Integer fileType, Long operaType, String textarea);

    void download(String objectName);
}
