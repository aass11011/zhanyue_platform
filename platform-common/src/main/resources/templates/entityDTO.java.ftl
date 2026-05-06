package ${basePackage}.entity.dto;

import entity.framework.com.zym.fastplatform.admin.BaseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
@Getter
@Setter
public class ${className}DTO extends BaseDTO {

<#list fields as field>
    /**
    * ${field.comment}
    */
    private ${field.type.javaType} ${field.name};

</#list>

}