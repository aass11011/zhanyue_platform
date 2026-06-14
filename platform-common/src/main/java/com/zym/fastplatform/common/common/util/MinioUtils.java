package com.zym.fastplatform.common.common.util;

import com.zym.fastplatform.common.common.config.MinioConfig;
import com.zym.fastplatform.common.common.framework.utils.StringUtils;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.UUID;


@Component
public class MinioUtils {
    private final MinioConfig config;
    private final MinioClient client;

    public MinioUtils(MinioConfig config, MinioClient client) {
        this.config = config;
        this.client = client;
    }


    public String upload(MultipartFile file){
        try {
            return upload(file.getOriginalFilename(),file.getContentType(),file.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private String upload(String fileName, String contentType, byte[] data) {
        try {
            return upload(config.getBucket(),fileName,contentType,new ByteArrayInputStream(data),data.length);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String upload(String bucket, String fileName, String contentType, ByteArrayInputStream byteArrayInputStream, int length) {
        try {
            client.putObject(PutObjectArgs.builder().bucket(bucket).contentType(contentType).stream(byteArrayInputStream,length,-1).object(fileName).build());
            return "/"+bucket+"/"+fileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public String uploadToBucket(String bucket, MultipartFile file) {
        try {
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isEmpty()) {
                throw new IllegalArgumentException("文件名不能为空");
            }

            // 生成唯一文件名
            String fileExtension = StringUtils.getFileExtension(originalFilename);
            String fileName = UUID.randomUUID().toString()+"." + fileExtension;

            return upload(bucket, fileName, file.getContentType(), new ByteArrayInputStream(file.getBytes()), file.getBytes().length);
        } catch (Exception e) {
            throw new RuntimeException("文件上传失败: " + e.getMessage(), e);
        }
    }

    public void delete(String fileName) {
        delete(config.getBucket(), fileName);
    }

    public void delete(String bucket, String fileName) {
        try {
            client.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(fileName)
                    .build());
        } catch (Exception e) {
            throw new RuntimeException("文件删除失败: " + e.getMessage(), e);
        }
    }
}
