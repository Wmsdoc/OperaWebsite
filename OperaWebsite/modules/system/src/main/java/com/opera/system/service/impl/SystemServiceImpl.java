package com.opera.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.opera.system.domain.Advert;
import com.opera.system.domain.Notice;
import com.opera.system.service.SystemService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class SystemServiceImpl implements SystemService {

    /**
     * 用于操作 Notice，Advert 类 的Redis工具类
     */
    @Resource
    private RedisTemplate redisTemplate;

    @Value("${system.noticeKey}")
    private String noticeKey;
    @Value("${system.advertKey}")
    private String advertKey;

    @Override
    public Page<Notice> getNotice(Integer pageNum, Integer pageSize) {
        //从 Redis 中获取公告信息
        List<Notice> list = redisTemplate.opsForList().range(noticeKey, (long) pageSize * (pageNum - 1),
                (long) pageSize * (pageNum - 1) + pageSize - 1);
        //获取总条数
        Long total = redisTemplate.opsForList().size(noticeKey);
        Page<Notice> page = new Page<>();
        page.setRecords(list);
        if (total == null)
            total = 0L;
        page.setTotal(total);
        return page;
    }

    @Override
    public Boolean addNotice(Notice notice) {
        //将公告信息存入 Redis
        notice.setNoticeId(UUID.randomUUID().toString());
        notice.setNoticeCreatedAt(new Date());
        redisTemplate.opsForList().leftPush(noticeKey, notice);
        return true;
    }


    @Override
    public Boolean deleteNotice(String key) {
        //将公告信息存入 Redis
        List<Notice> notices = redisTemplate.opsForList().range(noticeKey, 0, -1);
        assert notices != null;
        for (Notice notice : notices) {
            if (notice.getNoticeId().equals(key)) {
                redisTemplate.opsForList().remove(noticeKey, 1, notice);
                break;
            }
        }
        return true;
    }

    @Override
    public Page<Advert> getAdvert(Integer pageNum, Integer pageSize) {
        //从 Redis 中获取广告信息
        List<Advert> list = redisTemplate.opsForList().range(advertKey, (long) pageSize * (pageNum - 1),
                (long) pageSize * (pageNum - 1) + pageSize - 1);
        //获取总条数
        Long total = redisTemplate.opsForList().size(advertKey);
        Page<Advert> page = new Page<>();
        page.setRecords(list);
        if (total == null)
            total = 0L;
        page.setTotal(total);
        return page;
    }

    @Override
    public Boolean addAdvert(Advert advert) {
        advert.setAdvertId(UUID.randomUUID().toString());
        advert.setAdvertCreatedAt(new Date());
        redisTemplate.opsForList().leftPush(advertKey, advert);
        return true;
    }

    @Override
    public Boolean deleteAdvert(String key) {
        List<Advert> adverts = redisTemplate.opsForList().range(advertKey, 0, -1);
        assert adverts != null;
        for (Advert advert : adverts) {
            if (advert.getAdvertId().equals(key)) {
                redisTemplate.opsForList().remove(advertKey, 1, advert);
                break;
            }
        }
        return true;
    }


}
