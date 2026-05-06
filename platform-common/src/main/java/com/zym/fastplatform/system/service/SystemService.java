package com.zym.fastplatform.system.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SystemService {
    String upload(MultipartFile file) throws IOException;

    String uploadToBucket(String bucket, MultipartFile file);

    void deleteFile(String fileName);
}
