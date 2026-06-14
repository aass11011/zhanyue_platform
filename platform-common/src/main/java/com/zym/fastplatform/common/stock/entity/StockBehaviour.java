package com.zym.fastplatform.common.stock.entity;

import com.zym.fastplatform.common.common.framework.annotation.Url;
import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "stock_behaviour")
@AttributeOverrides({
        @AttributeOverride(name = "createdBy", column = @Column(name = "created_by")),
        @AttributeOverride(name = "updatedBy", column = @Column(name = "updated_by")),
        @AttributeOverride(name = "remark", column = @Column(name = "remark"))
})
public class StockBehaviour extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ColumnDefault("nextval('stock_behaviour_id_seq')")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "analysis", length = Integer.MAX_VALUE)
    private String analysis;

    @Column(name = "trading_plan", length = Integer.MAX_VALUE)
    private String tradingPlan;

    @Column
    private String stockCode;
    @OneToMany(targetEntity = StockBehaviourSticky.class, mappedBy = "stockBehaviour", cascade = CascadeType.ALL, orphanRemoval = true)
    @Url(collection = true)
    private List<StockBehaviourSticky> stickFormList;
}