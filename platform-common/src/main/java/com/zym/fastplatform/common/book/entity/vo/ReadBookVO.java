package com.zym.fastplatform.common.book.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadBookVO {

    /**
    * 
    */
    private Integer id;

    /**
    * 书名
    */
    private String book;

    /**
    * tag
    */
    private String tags;
    private String remark;

}