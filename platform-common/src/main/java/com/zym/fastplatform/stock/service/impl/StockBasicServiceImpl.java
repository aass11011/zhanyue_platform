// 文件路径: src/main/java/com/zym/fastplatform/stock/service/impl/StockBasicServiceImpl.java
package com.zym.fastplatform.stock.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.builder.ExcelReaderSheetBuilder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zym.fastplatform.common.util.MinioUtils;
import com.zym.fastplatform.framework.exception.ZException;
import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.framework.utils.PictureUtil;
import com.zym.fastplatform.framework.utils.StringUtils;
import com.zym.fastplatform.stock.convert.StockBasicConvertMapper;
import com.zym.fastplatform.stock.dao.StockBasicDao;
import com.zym.fastplatform.stock.entity.StockBasic;
import com.zym.fastplatform.stock.entity.dto.StockBasicDTO;
import com.zym.fastplatform.stock.entity.vo.StockBasicVO;
import com.zym.fastplatform.stock.service.StockBasicService;
import com.zym.fastplatform.system.dao.SysDictDataDao;
import com.zym.fastplatform.system.entity.SysDictData;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.ServletOutputStream;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class StockBasicServiceImpl extends BaseServiceImpl<StockBasicDao, StockBasic, StockBasicConvertMapper, StockBasicDTO, StockBasicVO> implements StockBasicService {
    @Autowired
    private SysDictDataDao sysDictDataDao;
    @Autowired
    private MinioUtils minioUtils;

    @Override
    @Transactional
    public void save(StockBasicDTO dto) {
        StockBasic entity = convertMapper.toEntity(dto);
        String stockCode = entity.getStockCode();
        if(entity.getId()== null){
            StockBasic stockBasic = dao.findByStockCode(stockCode);
            if(stockBasic != null){
                throw new ZException("股票代码已存在");
            }
        }
        fillExchangeAndMarket(stockCode, entity);
        if(dto.getConceptList() != null && !dto.getConceptList().isEmpty()){
            entity.setConcept(StringUtils.join(dto.getConceptList(),","));
            fillDictDataAboutConcept(dto.getConceptList());
        }else {
            entity.setConcept("");
        }
        if(StringUtils.isEmpty(entity.getLogo())){
            byte[] logoBytes = PictureUtil.generateLogoBytes(entity.getStockFullName());
            String fileName = "logo_" + System.currentTimeMillis() + ".png";
            String logoUrl = minioUtils.upload("img",fileName, "image/png", new ByteArrayInputStream(logoBytes),logoBytes.length);
            // 保存URL到实体
            entity.setLogo(logoUrl);
        }
        dao.save(entity);
    }

    public void fillDictDataAboutConcept(List<String> conceptList) {
        for (String concept : conceptList) {
            SysDictData sysDictData = sysDictDataDao.findByDictNameAndTypeId(concept, "a7aaaa5a-bf17-4609-9c1d-5f7de719e478");
            if(sysDictData == null){
                sysDictData = new SysDictData();
                sysDictData.setDictName(concept);
                sysDictData.setDictType("股票概念");
                sysDictData.setTypeId("a7aaaa5a-bf17-4609-9c1d-5f7de719e478");
                sysDictData.setSortOrder(0);
                sysDictData.setStatus((byte) 1);
                sysDictDataDao.save(sysDictData);
            }
        }
    }
    @Override
    public void fillExchangeAndMarket(String stockCode, StockBasic entity) {
        if(stockCode.startsWith("6")){
            entity.setExchange("上交所");
            if (stockCode.startsWith("60")){
                entity.setMarketType("主板");
            } else if (stockCode.startsWith("688")) {
                entity.setMarketType("科创板");
            }
        }else if(stockCode.startsWith("0")){
            entity.setExchange("深交所");
            entity.setMarketType("主板");
        } else if (stockCode.startsWith("30")) {
            entity.setExchange("深交所");
            entity.setMarketType("创业板");
        } else if (stockCode.startsWith("920")) {
            entity.setExchange("上交所");
        } else {
            log.error("股票代码格式错误:{}",stockCode);
            throw new ZException("股票代码格式错误");
        }
    }

    @Override
    @Transactional
    public void getByStockCode(String stockCode) {
        // 首先从数据库查询
        StockBasic stockBasic = dao.findByStockCode(stockCode);
        
        // 如果不存在，从akshare获取
        if (stockBasic == null) {
            try {
                // 调用Python脚本获取股票信息
                // 构建脚本路径
                String pythonScriptPath = System.getProperty("user.dir") + "/python/get_stock_basic.py";
                // 检查Python是否可用
                try {
                    Process checkProcess = Runtime.getRuntime().exec( "python --version");
                    int exitCode = checkProcess.waitFor();
                    if (exitCode != 0) {
                        throw new ZException("Python解释器未找到，请确保Docker容器中已安装Python");
                    }
                } catch (Exception e) {
                    throw new ZException("Python解释器未找到，请确保Docker容器中已安装Python: " + e.getMessage());
                }
                
                Process process = Runtime.getRuntime().exec("python " + pythonScriptPath + " " + stockCode);
                
                // 读取脚本输出
                java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getInputStream(), java.nio.charset.StandardCharsets.UTF_8)
                );
                java.io.BufferedReader errorReader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(process.getErrorStream(), java.nio.charset.StandardCharsets.UTF_8)
                );
                
                // 读取所有输出行
                StringBuilder outputBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    outputBuilder.append(line);
                }
                String output = outputBuilder.toString();
                
                // 读取所有错误行
                StringBuilder errorBuilder = new StringBuilder();
                while ((line = errorReader.readLine()) != null) {
                    errorBuilder.append(line).append("\n");
                }
                String error = errorBuilder.toString();
                
                process.waitFor();
                
                if (!error.isEmpty()) {
                    log.error("Python脚本执行错误: {}", error);
                    throw new ZException("获取股票信息失败: " + error);
                }
                
                if (output != null) {
                    // 解析JSON结果
                    JSONObject jsonObject = JSON.parseObject(output);
                    
                    if (jsonObject.containsKey("error")) {
                        throw new ZException(jsonObject.getString("error"));
                    }
                    
                    // 创建StockBasic对象
                    stockBasic = new StockBasic();
                    stockBasic.setStockCode(stockCode);
                    stockBasic.setStockShortName(jsonObject.getString("stockShortName"));
                    stockBasic.setStockFullName(jsonObject.getString("stockFullName"));
                    stockBasic.setIndustry(jsonObject.getString("industry"));
                    
                    // 填充交易所和市场类型
                    fillExchangeAndMarket(stockCode, stockBasic);
                    
                    // 生成logo
                    byte[] logoBytes = PictureUtil.generateLogoBytes(stockBasic.getStockFullName());
                    String fileName = "logo_" + System.currentTimeMillis() + ".png";
                    String logoUrl = minioUtils.upload("img", fileName, "image/png", new ByteArrayInputStream(logoBytes), logoBytes.length);
                    stockBasic.setLogo(logoUrl);
                    
                    // 保存到数据库
                    dao.save(stockBasic);
                }
            } catch (Exception e) {
                log.error("获取股票信息失败: {}", e.getMessage());
                throw new ZException("获取股票信息失败: " + e.getMessage());
            }
        }
    }

    @Transactional
    public void saveAll(List<StockBasic> list) {
        for (StockBasic entity : list) {
            fillExchangeAndMarket(entity.getStockCode(), entity);
            if (StringUtils.isNotBlank(entity.getConcept())){
                fillDictDataAboutConcept(List.of(entity.getConcept().split(",")));
            }
        }
        dao.saveAll(list);
    }

    private class StockBasicExcelListener extends AnalysisEventListener<StockBasicDTO> {
        @Getter
        private final List<StockBasic> list = new ArrayList<>();
        @Override
        public void invoke(StockBasicDTO stockBasic, AnalysisContext analysisContext) {
            StockBasic entity = convertMapper.toEntity(stockBasic);
            list.add(entity);
        }

        @Override
        public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        }
    }


    @Override
    @Transactional
    public void importData(MultipartFile file) {
        try {
            // 创建监听器
            StockBasicExcelListener listener = new StockBasicExcelListener();

            // 构建Excel读取器
            ExcelReaderBuilder readerBuilder = EasyExcel.read(file.getInputStream(), StockBasic.class, listener);
            ExcelReaderSheetBuilder sheetBuilder = readerBuilder.sheet();

            // 读取Excel数据
            sheetBuilder.doRead();

            // 获取解析后的数据
            List<StockBasic> stockBasicList = listener.getList();
            saveAll(stockBasicList);
            // 批量保存数据

        } catch (IOException e) {
            throw new ZException("Excel文件读取失败：" + e.getMessage());
        }
    }

    @Override
    public void exportData(ServletOutputStream outputStream,StockBasicDTO stockBasicDTO) {
        Specification<StockBasic> specification = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(StringUtils.isNotBlank(stockBasicDTO.getStockCode())){
                predicates.add(cb.like(root.get("stockCode"), "%" + stockBasicDTO.getStockCode() + "%"));
            }
            // 根据股票简称查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getStockShortName())) {
                predicates.add(cb.like(root.get("stockShortName"), "%" + stockBasicDTO.getStockShortName() + "%"));
            }

            // 根据交易所查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getExchange())) {
                predicates.add(cb.equal(root.get("exchange"), stockBasicDTO.getExchange()));
            }

            // 根据市场类型查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getMarketType())) {
                predicates.add(cb.equal(root.get("marketType"), stockBasicDTO.getMarketType()));
            }

            // 根据行业查询
            if (org.apache.commons.lang3.StringUtils.isNotBlank(stockBasicDTO.getIndustry())) {
                predicates.add(cb.like(root.get("industry"), "%" + stockBasicDTO.getIndustry() + "%"));
            }
            if(predicates.isEmpty()){
                return null;
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };

        List<StockBasic> list = dao.findAll(specification);
        EasyExcel.write(outputStream, StockBasic.class).sheet("股票基本信息").doWrite(list);
    }
}