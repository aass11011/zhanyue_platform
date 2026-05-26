package com.zym.fastplatform.admin.system.service.impl;

import com.zym.fastplatform.common.util.MinioUtils;
import com.zym.fastplatform.admin.system.service.SystemService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class SystemServiceImpl implements SystemService {
    private final MinioUtils minioUtils;

    public SystemServiceImpl(MinioUtils minioUtils) {
        this.minioUtils = minioUtils;
    }

    @Override
    public String upload(MultipartFile file) throws IOException {
        String imgDirPath = System.getProperty("user.dir") + "\\img";
        File imgDir = new File(imgDirPath);
        if (!imgDir.exists()) {
            imgDir.mkdirs();
        }

        // 生成唯一的文件名
        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String fileName = UUID.randomUUID().toString() + fileExtension;
        String filePath = imgDirPath + "\\" + fileName;

        // 保存文件
        File dest = new File(filePath);
        file.transferTo(dest);

        // 返回相对路径
        return "/img/" + fileName;
    }

    @Override
    public String uploadToBucket(String bucket, MultipartFile file) {
        return minioUtils.uploadToBucket(bucket,file);
    }

    @Override
    public void deleteFile(String fileName) {
        minioUtils.delete(fileName);
    }
}
