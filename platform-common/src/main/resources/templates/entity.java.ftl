package ${basePackage}.entity;

import entity.framework.com.zym.fastplatform.admin.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "${tableName}")
@Getter
@Setter
public class ${className} extends BaseEntity {

    /**
    * 主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<#list fields as field>
    /**
    * ${field.comment}
    */
    @Column
    private ${field.type.javaType} ${field.name};

</#list>

}