package com.zym.fastplatform.stock.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class ByteEnumConverter implements Converter<Byte> {
    @Override
    public Class<Byte> supportJavaTypeKey() {
        return Byte.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Byte convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        // 从Excel读取数据时，将字符串转换为Byte
        String value = cellData.getStringValue();
        if ("正常".equals(value)) {
            return 1;
        } else if ("异常".equals(value)) {
            return 0;
        }
        return null;
    }

    @Override
    public WriteCellData<?> convertToExcelData(Byte value, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) {
        // 将Byte转换为Excel字符串
        if (value == null) {
            return new WriteCellData<>("");
        } else if (value == 1) {
            return new WriteCellData<>("正常");
        } else if (value == 0) {
            return new WriteCellData<>("异常");
        }
        return new WriteCellData<>("");
    }
}
