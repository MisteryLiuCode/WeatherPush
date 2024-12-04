package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class HandRowData extends BodyInfoRow {

    @ExcelProperty("手长")
    private BigDecimal column_1; 

    @ExcelProperty("手背长")
    private BigDecimal column_2; 

    @ExcelProperty("掌长")
    private BigDecimal column_3; 

    @ExcelProperty("拇指长")
    private BigDecimal column_4; 

    @ExcelProperty("食指长")
    private BigDecimal column_5; 

    @ExcelProperty("中指长")
    private BigDecimal column_6; 

    @ExcelProperty("无名指长")
    private BigDecimal column_7; 

    @ExcelProperty("小指长")
    private BigDecimal column_8; 

    @ExcelProperty("中指第一指节长")
    private BigDecimal column_9; 

    @ExcelProperty("手宽（四指）")
    private BigDecimal column_10; 

    @ExcelProperty("手最大宽（五指）")
    private BigDecimal column_11; 

    @ExcelProperty("拇指指关节宽")
    private BigDecimal column_12; 

    @ExcelProperty("食指近位指关节宽")
    private BigDecimal column_13; 

    @ExcelProperty("食指远位指关节宽")
    private BigDecimal column_14; 

    @ExcelProperty("中指近位指关节宽")
    private BigDecimal column_15; 

    @ExcelProperty("无名指近位指关节宽")
    private BigDecimal column_16; 

    @ExcelProperty("小指近位指关节宽")
    private BigDecimal column_17; 

    @ExcelProperty("掌厚")
    private BigDecimal column_18; 

}
