package com.zym.fastplatform.common.generator;

import java.util.Optional;

public enum ZFiledType {
    VARCHAR("varchar","String"),
    INT("int", "Integer"),
    BIGINT("bigint", "Long"),
    TINYINT("tinyint", "Integer"),
    DATETIME("datetime", "LocalDateTime"),
    DATE("date", "LocalDate"),
    TIMESTAMP("timestamp", "LocalDateTime"),
    TEXT("text", "String"),
    LONGTEXT("longtext", "String"),
    DECIMAL("decimal", "BigDecimal");
    private String dbType;
    private String javaType;

    ZFiledType(String dbType, String javaType) {
        this.dbType = dbType;
        this.javaType = javaType;
    }

    public static Optional<ZFiledType> of(String dbType){
        // 处理带有长度或精度信息的类型字符串，如 numeric(10,2)
        String cleanDbType = dbType.split("\\(")[0].toLowerCase();
        
        // 映射常见的PostgreSQL类型到我们的枚举类型
        switch (cleanDbType) {
            case "int4":
            case "integer":
                cleanDbType = "int";
                break;
            case "numeric":
                cleanDbType = "decimal";
                break;
        }
        
        for (ZFiledType fieldType : ZFiledType.values()) {
            if(fieldType.getDbType().equals(cleanDbType)){
                return Optional.of(fieldType);
            }
        }
        return Optional.empty();
    }
    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}