package com.zym.fastplatform.book.entity;

import com.zym.fastplatform.framework.annotation.Url;
import com.zym.fastplatform.framework.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "read_note")
@Getter
@Setter
public class ReadNote extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
    * 
    */
    @Column
    private Long bookId;

    /**
    * 
    */
    @Column
    private String title;

    /**
    * 
    */
    @Column
    private String tags;

    /**
    * 内容
    */
    @Column
    @Url(complete = false)
    private String content;


}