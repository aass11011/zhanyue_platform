// 文件路径: src/main/java/com/zym/fastplatform/stock/entity/vo/StockBasicVO.java
package com.zym.fastplatform.common.stock.entity.vo;

import com.zym.fastplatform.common.stock.entity.StockConceptRel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
    private Byte status;
    private LocalDateTime createdTime;
    private String remark;
    private String logo;
    private String logoFilename;
    private List<StockConceptRel> stockConceptList;
}
