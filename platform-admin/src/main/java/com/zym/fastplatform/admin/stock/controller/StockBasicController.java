// 文件路径: src/main/java/com/zym/fastplatform/stock/controller/StockBasicController.java
package com.zym.fastplatform.admin.stock.controller;


import com.zym.fastplatform.admin.framework.controller.BaseController;
import com.zym.fastplatform.common.stock.entity.StockBasic;
import com.zym.fastplatform.common.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.common.stock.entity.vo.StockBasicVO;
import com.zym.fastplatform.common.stock.service.StockBasicService;
import com.zym.fastplatform.common.common.framework.entity.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/stock/basic")
public class StockBasicController extends BaseController<StockBasicService, StockBasic, StockBasicDTO, StockBasicVO> {

    @PostMapping("/import")
    public Result<Void> importData(@RequestPart("file") MultipartFile file) {
        service.importData(file);
        return Result.ok();
    }

    @GetMapping("/template")
    public void downloadTemplate(HttpServletResponse response) {
        // 模板文件路径
        String templatePath = "file/pattern/股票基本信息.xlsx";

        try {
            // 获取资源文件
            ClassPathResource resource = new ClassPathResource(templatePath);

            // 设置响应头
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=股票基本信息模板.xlsx");
            response.setHeader("Cache-Control", "no-cache");

            // 读取文件并写入响应流
            try (InputStream inputStream = resource.getInputStream();
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                outputStream.flush();
            }
        } catch (IOException e) {
            log.error("下载模板失败: {}", e.getMessage(), e);
        }
    }

    @GetMapping("/export")
    public void exportData(HttpServletResponse response, StockBasicDTO stockBasicDTO) {
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=股票基本信息导出.xlsx");
            response.setHeader("Cache-Control", "no-cache");
            service.exportData(response.getOutputStream(),stockBasicDTO);
        } catch (IOException e) {
            log.error("导出数据失败: {}", e.getMessage(), e);
        }
    }

    @GetMapping("get/web/{stockCode}")
    public Result<Void> getByStockCode(@PathVariable String stockCode) {
        service.getByStockCode(stockCode);
        return Result.ok();
    }

    @GetMapping("/leading-concepts")
    public Result<Map<String, List<StockBasicVO>>> groupByLeadingStockConcept(@RequestParam(required = false) String keyword) {
        return Result.ok(service.groupByLeadingStockConcept(keyword));
    }

    @GetMapping("/concepts")
    public Result<Map<String, List<StockBasicVO>>> groupByConcept(@RequestParam(required = false) String keyword) {
        return Result.ok(service.groupByConcept(keyword));
    }
}