package com.opera.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.system.domain.Advert;
import com.opera.system.domain.Notice;
import org.springframework.web.multipart.MultipartFile;

public interface SystemService {

    /**
     * 网站公告管理
     */
    Page<Notice> getNotice(Integer pageNum, Integer pageSize);

    Boolean addNotice(Notice notice);

    Boolean deleteNotice(String key);

    Page<Advert> getAdvert(Integer pageNum, Integer pageSize);

    Boolean addAdvert(Advert advert);

    Boolean deleteAdvert(String key);
}
