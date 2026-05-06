package com.zym.fastplatform.book.entity.dto;

import com.zym.fastplatform.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadNoteDTO extends BaseDTO {

    /**
    * 
    */
    private Integer id;

    /**
    * 
    */
    private Long bookId;

    /**
    * 
    */
    private String title;

    /**
    * 
    */
    private String tags;

    /**
    * 内容
    */
    private String content;


}