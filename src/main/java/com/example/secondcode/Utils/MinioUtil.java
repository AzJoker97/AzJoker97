package com.example.secondcode.Utils;

import io.minio.*;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Component
public class MinioUtil {
    private final Logger logger = LoggerFactory.getLogger(MinioUtil.class);

    @Value("yun")
    private String bucketName;

    private final MinioClient minioClient;


    public MinioUtil(MinioClient minioClient){
        this.minioClient = minioClient;
    }

    /*检查桶是否存在*/
    public Boolean bucketExists(String bucketName){
        Boolean tag = false;
        try{
            tag = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (tag){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return  false;
        }
        return false;
    }

    /*获取文件流*/
    public InputStream getFileInputStream(String fileName,String bucketName){
        try {
            return minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).object(fileName).build());
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
    /*创建存储桶*/
    public String createBucket(String bucketName){
        try {
            if (bucketName.isBlank()){
                return ("桶名字不能为空");
            }
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (isExist) {
                logger.info("Bucket [] already exists.", bucketName);
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        }catch (Exception exception){
            exception.printStackTrace();
            logger.error(exception.getMessage());
        }
        return ("创建成功");
    }

    /*删除存储桶以及文件*/
    public String deleteBucket(String bucketName){
        try {
            if (bucketName.isBlank()) {
                return ("名字不能为空");
            }
            boolean isExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (isExists) {
                minioClient.removeBucket(RemoveBucketArgs.builder().bucket(bucketName).build());
                minioClient.deleteBucketEncryption(DeleteBucketEncryptionArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return ("删除成功");
    }


    /*获取文件的绝对地址*/
    public String getPreviewFileUrl(String bucketName, String fileName) {
        try {
            return minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (Exception e) {
            e.printStackTrace();
            return " ";
        }
    }

    /*上传文件*/
    public void uploadFile(MultipartFile file) {
        try {
            boolean bucketExists = bucketExists(bucketName);
            if(!bucketExists){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
            String objectName = file.getOriginalFilename();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName)
                    .stream(file.getInputStream(), file.getInputStream().available(), -1)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*下载文件*/
    public InputStream downloadFile(String bucketName, String originalName, HttpServletResponse response) {
        try {
            InputStream file = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).build());
            String filename = new String(originalName.getBytes("ISO8859-1"), StandardCharsets.UTF_8);
            if (StringUtils.isNotBlank(originalName)) {
                filename = originalName;
            }
            response.setHeader("Content-Disposition", "attachment;filename=" + filename);
            ServletOutputStream servletOutputStream = response.getOutputStream();
            int len;
            byte[] buffer = new byte[1024];
            while ((len = file.read(buffer)) > 0) {
                servletOutputStream.write(buffer, 0, len);
            }
            servletOutputStream.flush();
            file.close();
            servletOutputStream.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
