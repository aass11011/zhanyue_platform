package com.zym.fastplatform.admin.system.controller;

import com.zym.fastplatform.framework.entity.Result;
import com.zym.fastplatform.admin.system.service.SystemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/system")
public class SystemController {
    @Autowired
    private SystemService systemService;
    @Operation(summary = "上传图片", description = "上传图片到服务器")
    @PostMapping("upload/img")
    public Result<String> upload(@RequestParam("file") MultipartFile file) throws IOException {
        String url = systemService.upload(file);
        return Result.ok(url);
    }

    @PostMapping("upload/to-bucket/{bucket}")
    @Operation(summary = "上传文件到指定bucket", description = "上传文件到指定的MinIO存储桶")
    public Result<String> uploadToBucket(
            @Parameter(description = "存储桶名称")
            @PathVariable String bucket,
            @Parameter(description = "上传的文件")
            @RequestParam("file") MultipartFile file) throws IOException {
        String url = systemService.uploadToBucket(bucket, file);
        return Result.ok(url);
    }

    @PostMapping("file/{fileName}")
    @Operation(summary = "删除文件", description = "从MinIO存储中删除指定文件")
    public Result<Void> deleteFile(
            @Parameter(description = "文件名")
            @PathVariable String fileName) {
        systemService.deleteFile(fileName);
        return Result.ok();
    }
}
