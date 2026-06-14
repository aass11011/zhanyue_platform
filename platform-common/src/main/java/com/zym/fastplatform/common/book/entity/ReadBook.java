package com.zym.fastplatform.common.book.entity;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "read_book")
@Getter
@Setter
public class ReadBook extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 书名
    */
    @Column
    private String book;

    /**
    * tag
    */
    @Column
    private String tags;


}