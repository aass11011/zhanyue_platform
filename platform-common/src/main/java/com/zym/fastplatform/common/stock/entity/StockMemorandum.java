package com.zym.fastplatform.common.stock.entity;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "stock_memorandum")
@AttributeOverrides({
        @AttributeOverride(name = "createdBy", column = @Column(name = "created_by")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "updated_by")),
        @AttributeOverride(name = "remark", column = @Column(name = "remark"))
})
public class StockMemorandum extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "stock_code")
    private String stockCode;

    @Column(name = "stock_name")
    private String stockName;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "content", length = Integer.MAX_VALUE)
    private String content;

    @Column(name = "remember_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rememberDate;
}
