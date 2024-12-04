package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class LowerLegRowData extends BodyInfoRow {

    @ExcelProperty("膝高")
    private BigDecimal column_1; 

    @ExcelProperty("胫骨点高")
    private BigDecimal column_2; 

    @ExcelProperty("腓骨头高")
    private BigDecimal column_3; 

    @ExcelProperty("腘窝高")
    private BigDecimal column_4; 

    @ExcelProperty("小腿肚高")
    private BigDecimal column_5; 

    @ExcelProperty("内踝高")
    private BigDecimal column_6; 

    @ExcelProperty("外踝高")
    private BigDecimal column_7; 

    @ExcelProperty("小腿长")
    private BigDecimal column_8; 

    @ExcelProperty("膝宽")
    private BigDecimal column_9; 

    @ExcelProperty("腿肚宽")
    private BigDecimal column_10; 

    @ExcelProperty("踝上宽")
    private BigDecimal column_11; 

    @ExcelProperty("膝厚")
    private BigDecimal column_12; 

    @ExcelProperty("腿肚厚")
    private BigDecimal column_13; 

    @ExcelProperty("踝上厚")
    private BigDecimal column_14; 

    @ExcelProperty("小腿加足高")
    private BigDecimal column_15; 

    @ExcelProperty("两膝宽")
    private BigDecimal column_16; 

    @ExcelProperty("内踝足跟距")
    private BigDecimal column_17; 

    @ExcelProperty("内外踝间宽")
    private BigDecimal column_18; 

}
