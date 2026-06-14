package com.zym.fastplatform.common.scripture.entity.dto;

import com.zym.fastplatform.common.common.framework.entity.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScriptureDTO extends BaseDTO {

    /**
    * 
    */
    private Integer id;

    /**
    * 标题
    */
    private String title;

    /**
    * 作者
    */
    private String author;

    /**
    * 文章内容
    */
    private String content;


}