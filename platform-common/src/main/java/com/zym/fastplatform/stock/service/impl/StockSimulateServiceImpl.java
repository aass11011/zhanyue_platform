package com.zym.fastplatform.stock.service.impl;

import com.zym.fastplatform.framework.service.impl.BaseServiceImpl;
import com.zym.fastplatform.stock.convert.StockSimulateConvertMapper;
import com.zym.fastplatform.stock.dao.StockSimulateDao;
import com.zym.fastplatform.stock.dao.StockSimulateDataDao;
import com.zym.fastplatform.stock.entity.StockSimulate;
import com.zym.fastplatform.stock.entity.StockSimulateData;
import com.zym.fastplatform.stock.entity.dto.StockSimulateDTO;
import com.zym.fastplatform.stock.entity.vo.StockSimulateVO;
import com.zym.fastplatform.stock.service.StockSimulateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class StockSimulateServiceImpl extends BaseServiceImpl<StockSimulateDao, StockSimulate, StockSimulateConvertMapper, StockSimulateDTO, StockSimulateVO> implements StockSimulateService {
    @Autowired
    private StockSimulateDataDao stockSimulateDataDao;




    @Override
    @Transactional
    public void importData(MultipartFile file, Long id) {
        List<StockSimulateData> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            boolean isFirstLine = true;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while ((line = br.readLine()) != null) {
                // 跳过第一行（标题行）
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                // 解析 CSV 行（按逗号分割）
                String[] columns = line.split(",");
                if (columns.length < 12) {
                    log.warn("CSV 行数不足，跳过该行：{}", line);
                    continue;
                }

                try {
                    StockSimulateData entity = new StockSimulateData();
                    entity.setTradeDate(LocalDate.parse(columns[0].trim(), formatter));
                    entity.setStockCode(columns[1].trim());
                    entity.setOpenPrice(new BigDecimal(columns[2].trim()));
                    entity.setClosePrice(new BigDecimal(columns[3].trim()));
                    entity.setHign(new BigDecimal(columns[4].trim()));
                    entity.setLow(new BigDecimal(columns[5].trim()));
                    entity.setVolume(Integer.parseInt(columns[6].trim()));
                    entity.setAccount(new BigDecimal(columns[7].trim()));
                    entity.setAmplitude(new BigDecimal(columns[8].trim()));
                    entity.setPricePer(new BigDecimal(columns[9].trim()));
                    entity.setPriceChange(new BigDecimal(columns[10].trim()));
                    entity.setTurnover(new BigDecimal(columns[11].trim()));
                    entity.setSimulateId(id);
                    dataList.add(entity);
                } catch (Exception e) {
                    log.warn("解析 CSV 行失败：{}, 错误：{}", line, e.getMessage());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stockSimulateDataDao.saveAll(dataList);
    }
}