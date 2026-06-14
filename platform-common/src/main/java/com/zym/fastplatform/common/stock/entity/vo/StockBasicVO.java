// 文件路径: src/main/java/com/zym/fastplatform/stock/entity/vo/StockBasicVO.java
package com.zym.fastplatform.common.stock.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class StockBasicVO {
    private Long id;
    private String stockCode;
    private String stockShortName;
    private String stockFullName;
    private String exchange;
    private String marketType;
    private String industry;
    private String concept;
    private Byte status;
    private LocalDateTime createdTime;
    private String remark;
    private String logo;
    private String logoFilename;
}
