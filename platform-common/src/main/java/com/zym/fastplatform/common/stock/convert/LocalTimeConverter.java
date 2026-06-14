package com.zym.fastplatform.common.stock.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.zym.fastplatform.common.common.framework.utils.StringUtils;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * EasyExcel LocalTime 自定义转换器
 */
public class LocalTimeConverter implements Converter<LocalTime> {

    // 定义支持的时间格式
    private static final DateTimeFormatter[] FORMATTERS = {
            DateTimeFormatter.ofPattern("HH:mm"),
            DateTimeFormatter.ofPattern("H:mm")
    };

    @Override
    public Class<LocalTime> supportJavaTypeKey() {
        return LocalTime.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public LocalTime convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        String value = StringUtils.splitByWhitespace(cellData.getStringValue())[0];
        if (value == null || value.isEmpty()) {
            return null;
        }

        // 尝试使用不同的格式解析
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalTime.parse(value, formatter);
            } catch (DateTimeParseException e) {
                // 继续尝试下一个格式
            }
        }

        // 如果所有格式都解析失败，抛出异常
        throw new DateTimeParseException("无法解析时间字符串: " + value, value, 0);
    }

    @Override
    public WriteCellData<?> convertToExcelData(LocalTime value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        if (value == null) {
            return new WriteCellData<>("");
        }
        // 导出时使用标准格式
        return new WriteCellData<>(value.format(FORMATTERS[0]));
    }
}