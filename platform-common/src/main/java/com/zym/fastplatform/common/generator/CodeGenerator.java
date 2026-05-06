package com.zym.fastplatform.common.generator;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class CodeGenerator {
    private final JdbcTemplate jdbcTemplate;

    public CodeGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    /***
    * @author zhangym
    * @date 2024/9/20
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

    public void generateEntity(String entityPath, String basePackage, String tableName, String className){
        String sql = "SELECT a.attname AS column_name, " +
                     "pg_catalog.format_type(a.atttypid, a.atttypmod) AS data_type, " +
                     "col_description(a.attrelid, a.attnum) AS column_comment " +
                     "FROM pg_catalog.pg_attribute a " +
                     "WHERE a.attrelid = (SELECT oid FROM pg_catalog.pg_class WHERE relname = '" + tableName + "') " +
                     "AND a.attnum > 0 AND NOT a.attisdropped " +
                     "ORDER BY a.attnum";
        List<ZField> fields = jdbcTemplate.queryForList(sql).stream().map(this::buildFieldFromMap)
                .filter(field -> !Set.of("id", "createdTime", "createdBy", "updatedTime", "updatedBy", "isDeleted", "remark").contains(field.getName())).toList();
        Map<String, Object> params = Map.of("fields", fields, "className", className, "tableName", tableName, "basePackage", basePackage);
        generateClass(entityPath, "entity.java.ftl", className, params);
    }

    private void generateClass(String path, String templateName, String className, Map<String, Object> params) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassLoaderForTemplateLoading(CodeGenerator.class.getClassLoader(), "templates");
        try {
            Template template = configuration.getTemplate(templateName);
            Path p = Path.of(path);
            if (!Files.exists(p)){
                Files.createDirectories(p);
            }
            template.process(params, Files.newBufferedWriter(Path.of(path, className+".java")));
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }

    public ZField buildFieldFromMap(Map<String,Object> map){
        String columnName = map.get("column_name").toString();
        String columnType = map.get("data_type").toString();
        Object columnCommentObj = map.get("column_comment");
        String columnComment = columnCommentObj != null ? columnCommentObj.toString() : "";
        ZFiledType type = ZFiledType.of(columnType).orElse(ZFiledType.VARCHAR);
        columnName = underlineToCamel(columnName);
        return new ZField(columnName, type, columnComment);
    }

    private String pathToPackage(String path){
        return path.replace("/", "-").replace("\\", "-").replace("-", ".");
    }

    public void generate(String basePath, String tableName, String className){
        String basePackage = pathToPackage(basePath);
        
        // 生成实体类
        String entityPath = "src/main/java/" + basePath + "/entity/";
        generateEntity(entityPath, basePackage, tableName, className);
        
        // 生成DTO
        String dtoPath = "src/main/java/" + basePath + "/entity/dto/";
        generateDTO(dtoPath, basePackage, tableName, className);
        
        // 生成VO
        String voPath = "src/main/java/" + basePath + "/entity/vo/";
        generateVO(voPath, basePackage, tableName, className);
        
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

    private void generateDTO(String dtoPath, String basePackage, String tableName, String className) {
        String sql = "SELECT a.attname AS column_name, " +
                     "pg_catalog.format_type(a.atttypid, a.atttypmod) AS data_type, " +
                     "col_description(a.attrelid, a.attnum) AS column_comment " +
                     "FROM pg_catalog.pg_attribute a " +
                     "WHERE a.attrelid = (SELECT oid FROM pg_catalog.pg_class WHERE relname = '" + tableName + "') " +
                     "AND a.attnum > 0 AND NOT a.attisdropped " +
                     "ORDER BY a.attnum";
        List<ZField> fields = jdbcTemplate.queryForList(sql).stream().map(this::buildFieldFromMap)
                .filter(field -> !Set.of("createdTime", "createdBy", "updatedTime", "updatedBy", "isDeleted", "remark").contains(field.getName())).toList();
        Map<String, Object> params = Map.of("fields", fields, "className", className, "tableName", tableName, "basePackage", basePackage);
        generateClass(dtoPath, "entityDTO.java.ftl", className + "DTO", params);
    }

    private void generateVO(String voPath, String basePackage, String tableName, String className) {
        String sql = "SELECT a.attname AS column_name, " +
                     "pg_catalog.format_type(a.atttypid, a.atttypmod) AS data_type, " +
                     "col_description(a.attrelid, a.attnum) AS column_comment " +
                     "FROM pg_catalog.pg_attribute a " +
                     "WHERE a.attrelid = (SELECT oid FROM pg_catalog.pg_class WHERE relname = '" + tableName + "') " +
                     "AND a.attnum > 0 AND NOT a.attisdropped " +
                     "ORDER BY a.attnum";
        List<ZField> fields = jdbcTemplate.queryForList(sql).stream().map(this::buildFieldFromMap)
                .filter(field -> !Set.of("createdTime", "createdBy", "updatedTime", "updatedBy", "isDeleted", "remark").contains(field.getName())).toList();
        Map<String, Object> params = Map.of("fields", fields, "className", className, "tableName", tableName, "basePackage", basePackage);
        generateClass(voPath, "entityVO.java.ftl", className + "VO", params);
    }

    private void generateConvertMapper(String convertPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(convertPath, "baseConvertMapper.java.ftl", className + "ConvertMapper", params);
    }

    private void generateController(String controllerPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(controllerPath, "controller.java.ftl", className + "Controller", params);
    }

    private void generateService(String servicePath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(servicePath, "service.java.ftl", className + "Service", params);
    }

    private void generateServiceImpl(String serviceImplPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(serviceImplPath, "serviceImpl.java.ftl", className + "ServiceImpl", params);
    }

    private void generateDao(String daoPath, String basePackage, String className) {
        Map<String, Object> params = Map.of("className", className, "basePackage", basePackage);
        generateClass(daoPath, "dao.java.ftl", className + "Dao", params);
    }
}