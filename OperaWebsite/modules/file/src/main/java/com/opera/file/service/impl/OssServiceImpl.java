package com.opera.file.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.opera.api.activity.RemoteActivityService;
import com.opera.api.opera.RemoteOperaService;
import com.opera.api.system.RemoteSystemService;
import com.opera.file.service.OssService;
import com.opera.file.util.AliOssUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    @Resource
    private AliOssUtil aliOssUtil;

    @Resource
    private RemoteOperaService remoteOperaService;
    @Resource
    private RemoteActivityService remoteActivityService;
    @Resource
    private RemoteSystemService remoteSystemService;

    @Value("${alioss.images-bucket}")
    private String imagesBucket;
    @Value("${alioss.audio-bucket}")
    private String audioBucket;
    @Value("${alioss.video-bucket}")
    private String videoBucket;

    @Override
    public Boolean upload(MultipartFile file, Integer fileType, Long operaType, String textarea) {
        try {
            //获取原始的文件名
            String originalFilename = file.getOriginalFilename();
            //在oss中存储名字就是UUID + 文件的后缀名
            //设置存入OSS的文件名
            assert originalFilename != null;
            String objectName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            //获取当前登录用户的id
            Long accountId = StpUtil.getLoginIdAsLong();
            String fileUrl = null;
            String filename = null;
            boolean result = false;
            //判断文件类型 0 为图片 1 为音频 2 为视频
            switch (fileType) {
                case 0:
                    fileUrl = aliOssUtil.upload(file.getBytes(), imagesBucket + objectName);
                    //根据accountId获取 playgoerId
                    Long playgoerId = remoteSystemService.getPlaygoerIdByAccountId(accountId).getData();
                    //调用远程接口，将头像信息存入数据库
                    remoteActivityService.updateAvatar(accountId, playgoerId, fileUrl);
                    break;
                case 1:
                    //获取原始文件的名字（去除后缀）
                    filename = originalFilename.substring(0, file.getOriginalFilename().lastIndexOf("."));
                    fileUrl = aliOssUtil.upload(file.getBytes(), audioBucket + objectName);
                    //调用远程接口，将音频信息存入数据库
                    result = remoteOperaService.audioInsert(accountId, filename, textarea, fileUrl, operaType).getData();
                    break;
                case 2:
                    //获取原始文件的名字（去除后缀）
                    filename = originalFilename.substring(0, file.getOriginalFilename().lastIndexOf("."));
                    fileUrl = aliOssUtil.upload(file.getBytes(), videoBucket + objectName);
                    //调用远程接口，将视频信息存入数据库
                    result = remoteOperaService.videoInsert(accountId, filename, textarea, fileUrl, operaType).getData();
                    break;
                default:
                    break;
            }
            return result;
//            return aliOssUtil.upload(file.getBytes(), objectName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void download(String objectName) {
        aliOssUtil.download(objectName);
    }
}
