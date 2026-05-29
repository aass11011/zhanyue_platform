package com.zym.fastplatform.stock.entity;

import com.zym.fastplatform.framework.annotation.Url;
import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock_behaviour_sticky")
public class StockBehaviourSticky extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "image_url")
    @Url
    private String imageUrl;

    @Size(max = 255)
    @Column(name = "views")
    private String views;

    @Column(name = "behaviour_id")
    private Long behaviourId;

    @ManyToOne
    @JoinColumn(name = "behaviour_id",insertable = false,updatable = false)
    private StockBehaviour stockBehaviour;
}