package ${basePackage}.convert;

import ${basePackage}.entity.${className};
import ${basePackage}.entity.dto.${className}DTO;
import ${basePackage}.entity.vo.${className}VO;
import convert.framework.com.zym.fastplatform.admin.BaseConvertMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ${className}ConvertMapper extends BaseConvertMapper<${className}, ${className}VO, ${className}DTO> {


}