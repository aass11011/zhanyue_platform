package com.zym.fastplatform.common.scripture.entity;

import com.zym.fastplatform.common.common.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "scripture")
@Getter
@Setter
public class Scripture extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 标题
    */
    @Column
    private String title;

    /**
    * 作者
    */
    @Column
    private String author;

    /**
    * 文章内容
    */
    @Column
    private String content;


}