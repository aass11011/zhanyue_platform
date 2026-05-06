package com.zym.fastplatform.system.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class HideFlagConverter implements AttributeConverter<Boolean, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Boolean attribute) {
        if(attribute == null){
            return 1;
        }
        return attribute ? 2 : 1;
    }

    @Override
    public Boolean convertToEntityAttribute(Integer dbData) {
        return dbData != null && dbData == 2;
    }
}
