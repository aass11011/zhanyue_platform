package com.zym.fastplatform.book.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadBookDTO extends BaseDTO {

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


}