package com.zym.fastplatform.common.book.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
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