package com.opera.file.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DownloadFileRequest;
import com.aliyun.oss.model.DownloadFileResult;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.ByteArrayInputStream;

@Data
@AllArgsConstructor
//固定代码，CV直接使用
public class AliOssUtil {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

//    /**
//     * 图片上传，并进行处理图片
//     */
//    public String imageUpload(File file, String objectName) {
//
//        // 创建OSSClient实例。
//        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//        try {
//            // 将图片缩放为固定宽高100 px。
//            String style = "image/resize,m_fixed,w_100,h_100";
//            GetObjectRequest request = new GetObjectRequest(bucketName, objectName);
//            request.setProcess(style);
//            // 将处理后的图片命名为example-resize.jpg并保存到本地。
//            // 如果未指定本地路径只填写了本地文件名称（例如example-resize.jpg），则文件默认保存到示例程序所属项目对应本地路径中。
//            ossClient.getObject(request, file);
//        } catch (OSSException oe) {
//            System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                    + "but was rejected with an error response for some reason.");
//            System.out.println("Error Message:" + oe.getErrorMessage());
//            System.out.println("Error Code:" + oe.getErrorCode());
//            System.out.println("Request ID:" + oe.getRequestId());
//            System.out.println("Host ID:" + oe.getHostId());
//        } catch (ClientException ce) {
//            System.out.println("Caught an ClientException, which means the client encountered "
//                    + "a serious internal problem while trying to communicate with OSS, "
//                    + "such as not being able to access the network.");
//            System.out.println("Error Message:" + ce.getMessage());
//        } finally {
//            if (ossClient != null) {
//                ossClient.shutdown();
//            }
//        }
//
//    //文件访问路径规则 https://BucketName.Endpoint/ObjectName
//
//        return"https://"+
//    bucketName +
//            "."+
//    endpoint +
//            "/"+
//    objectName;
//}


    /**
     * 文件上传
     *
     * @param bytes      ：传入的文件要转为byte[]
     * @param objectName ：表示在oss中存储的文件名字。
     * @return 返回文件的访问路径
     */
    public String upload(byte[] bytes, String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(bytes));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        //文件访问路径规则 https://BucketName.Endpoint/ObjectName

        return "https://" +
                bucketName +
                "." +
                endpoint +
                "/" +
                objectName;
    }

    /**
     * 文件下载
     */
    public void download(String objectName) {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 请求10个任务并发下载。
            DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, objectName);
            // 指定Object下载到本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
            downloadFileRequest.setDownloadFile("D:\\localpath\\examplefile.txt");
            // 设置分片大小，单位为字节，取值范围为100 KB~5 GB。默认值为100 KB。
            downloadFileRequest.setPartSize(1 * 1024 * 1024);
            // 设置分片下载的并发数，默认值为1。
            downloadFileRequest.setTaskNum(10);
            // 开启断点续传下载，默认关闭。
            downloadFileRequest.setEnableCheckpoint(true);
            // 设置断点记录文件的完整路径，例如D:\\localpath\\examplefile.txt.dcp。
            // 只有当Object下载中断产生了断点记录文件后，如果需要继续下载该Object，才需要设置对应的断点记录文件。下载完成后，该文件会被删除。
            //downloadFileRequest.setCheckpointFile("D:\\localpath\\examplefile.txt.dcp");

            // 下载文件。
            DownloadFileResult downloadRes = ossClient.downloadFile(downloadFileRequest);
            // 下载成功时，会返回文件元信息。
            ObjectMetadata objectMetadata = downloadRes.getObjectMetadata();
            System.out.println(objectMetadata.getETag());
            System.out.println(objectMetadata.getLastModified());
            System.out.println(objectMetadata.getUserMetadata().get("meta"));
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (Throwable ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}
