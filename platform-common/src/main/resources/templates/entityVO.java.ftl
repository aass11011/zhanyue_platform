package ${basePackage}.entity.vo;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ${className}VO {

<#list fields as field>
    /**
    * ${field.comment}
    */
    private ${field.type.javaType} ${field.name};

</#list>

}