package com.zym.fastplatform.common.book.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReadNoteVO {

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}