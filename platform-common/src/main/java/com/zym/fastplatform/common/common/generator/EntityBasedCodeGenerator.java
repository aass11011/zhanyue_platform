package com.zym.fastplatform.common.common.generator;

import com.zym.fastplatform.common.common.framework.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class EntityBasedCodeGenerator {

    /***
    * @author zhangym
    * @date 2026/03/06
    * @description 下划线转驼峰
    */
    private String underlineToCamel(String str){
        StringBuilder result = new StringBuilder();
        String[] a = str.split("_");
        for (String s : a) {
            if(result.length()==0){
                result.append(s.toLowerCase());
            }else {
                result.append(s.substring(0,1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 根据给定的 Entity 类生成其余 Java 类
     * @param entityClass Entity 类
     */
    public void generate(Class<?> entityClass){
        String packageName = entityClass.getPackage().getName();
        String basePath = StringUtils.substringBeforeLast(packageName.replace(".", "/"),"/entity");
        String basePackage = StringUtils.substringBeforeLast(pathToPackage(basePath), ".entity");
        String className = entityClass.getSimpleName();
        
        // 分析 Entity 类的字段
        List<ZField> fields = analyzeEntityFields(entityClass);
        
        // 生成DTO
        String dtoPath = "src/main/java/" + basePath + "/entity/dto/";
        generateDTO(dtoPath, basePackage, className, fields);
        
        // 生成VO
        String voPath = "src/main/java/" + basePath + "/entity/vo/";
        generateVO(voPath, basePackage, className, fields);
        
        // 生成ConvertMapper
        String convertPath = "src/main/java/" + basePath + "/convert/";
        generateConvertMapper(convertPath, basePackage, className);

        // 生成Controller
        String controllerPath = "src/main/java/" + basePath + "/controller";
        generateController(controllerPath, basePackage, className);

        // 生成Service
        String servicePath = "src/main/java/" + basePath + "/service/";
        generateService(servicePath, basePackage, className);

        // 生成ServiceImpl
        String serviceImplPath = "src/main/java/" + basePath + "/service/impl/";
        generateServiceImpl(serviceImplPath, basePackage, className);

        // 生成Dao
        String daoPath = "src/main/java/" + basePath + "/dao/";
        generateDao(daoPath, basePackage, className);
    }

    /**
     * 分析 Entity 类的字段
     * @param entityClass Entity 类
     * @return 字段列表
     */
    private List<ZField> analyzeEntityFields(Class<?> entityClass) {
        List<ZField> fields = new ArrayList<>();
        
        // 获取所有字段，包括父类的字段
        Class<?> currentClass = entityClass;
        while (currentClass != null && currentClass != Object.class) {
            Field[] declaredFields = currentClass.getDeclaredFields();
            for (Field field : declaredFields) {
                // 跳过静态字段和 transient 字段
                if (java.lang.reflect.Modifier.isStatic(field.getModifiers()) || 
                    java.lang.reflect.Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                
                // 跳过基类中已有的字段
                if (Set.of("id", "createdTime", "createdBy", "updatedTime", "updatedBy", "isDeleted", "remark").contains(field.getName())) {
                    continue;
                }
                
                // 构建 ZField
                String fieldName = field.getName();
                ZFiledType fieldType = ZFiledType.of(field.getType().getSimpleName()).orElse(ZFiledType.VARCHAR);
                String fieldComment = "";
                
                // 尝试获取字段的注释（可以通过注解或其他方式获取）
                // 这里简化处理，实际项目中可以根据需要扩展
                
                fields.add(new ZField(fieldName, fieldType, fieldComment));
            }
            currentClass = currentClass.getSuperclass();
        }
        
        return fields;
    }

    /**
     * 生成实体类
     */
    private void generateEntity(String entityPath, String basePackage, String className, List<ZField> fields) {
        Map<String, Object> params = Map.of("fields", fields, "className", className, "tableName", className.toLowerCase(), "basePackage", basePackage);
        generateClass(entityPath, "entity.java.ftl", className, params);
    }

    /**
     * 生成DTO
     */
    private void generateDTO(String dtoPath, String basePackage, String className, List<ZField> fields) {
        Map<String, Object> params = Map.of("fields", fields, "className", className, "basePackage", basePackage);
        generateClass(dtoPath, "entityDTO.java.ftl", className + "DTO", params);
    }

    /**
     * 生成VO
     */
    private void generateVO(String voPath, String basePackage, String className, List<ZField> fields) {
        Map<String, Object> params = Map.of("fields", fields, "className", className, "basePackage", basePackage);
        generateClass(voPath, "entityVO.java.ftl", className + "VO", params);
    }

    /**
     * 生成ConvertMapper
     */
    private void generateConvertMapper(String convertPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(convertPath, "baseconvertmapper.java.ftl", className + "ConvertMapper", params);
    }

    /**
     * 生成Controller
     */
    private void generateController(String controllerPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(controllerPath, "controller.java.ftl", className + "Controller", params);
    }

    /**
     * 生成Service
     */
    private void generateService(String servicePath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(servicePath, "service.java.ftl", className + "Service", params);
    }

    /**
     * 生成ServiceImpl
     */
    private void generateServiceImpl(String serviceImplPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(serviceImplPath, "serviceImpl.java.ftl", className + "ServiceImpl", params);
    }

    /**
     * 生成Dao
     */
    private void generateDao(String daoPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(daoPath, "dao.java.ftl", className + "Dao", params);
    }

    /**
     * 生成类文件
     */
    private void generateClass(String path, String templateName, String className, Map<String, Object> params) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassLoaderForTemplateLoading(EntityBasedCodeGenerator.class.getClassLoader(), "templates");
        try {
            Template template = configuration.getTemplate(templateName);
            Path p = Path.of(path);
            if (!Files.exists(p)){
                Files.createDirectories(p);
            }
            template.process(params, Files.newBufferedWriter(Path.of(path, className + ".java")));
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 路径转包名
     */
    private String pathToPackage(String path) {
        return path.replace("/", "-").replace("\\", "-").replace("-", ".");
    }
}