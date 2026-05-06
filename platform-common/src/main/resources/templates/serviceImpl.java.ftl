package ${basePackage}.service.impl;

import ${basePackage}.convert.${className}ConvertMapper;
import ${basePackage}.dao.${className}Dao;
import ${basePackage}.entity.${className};
import ${basePackage}.entity.dto.${className}DTO;
import ${basePackage}.entity.vo.${className}VO;
import ${basePackage}.service.${className}Service;
import impl.service.framework.com.zym.fastplatform.admin.BaseServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class ${className}ServiceImpl extends BaseServiceImpl<${className}Dao, ${className}, ${className}ConvertMapper, ${className}DTO, ${className}VO> implements ${className}Service {



}