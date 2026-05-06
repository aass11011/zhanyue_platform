package com.zym.fastplatform.framework.handler;

import jakarta.persistence.AttributeConverter;
import org.postgresql.util.PGobject;

import java.sql.SQLException;

public class JsonbToStringHandler implements AttributeConverter<String, Object> {

    @Override
    public Object convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }
        PGobject pgObject = new PGobject();
        pgObject.setType("jsonb");
        try {
            pgObject.setValue(attribute);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pgObject;
    }

    @Override
    public String convertToEntityAttribute(Object dbData) {
        // PG jsonb → Java String
        return dbData == null ? null : dbData.toString();
    }
}
