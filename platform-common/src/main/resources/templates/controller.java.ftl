package ${basePackage}.controller;

import controller.framework.com.zym.fastplatform.admin.BaseController;
import ${basePackage}.entity.${className};
import ${basePackage}.entity.dto.${className}DTO;
import ${basePackage}.entity.vo.${className}VO;
import ${basePackage}.service.${className}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/${className?uncap_first}")
@Slf4j
public class ${className}Controller extends BaseController<${className}Service, ${className}, ${className}DTO, ${className}VO> {


}