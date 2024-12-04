package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LowerTorsoRowData extends BodyInfoRow {

    @ExcelProperty("腰点高")
    private BigDecimal column_1;

    @ExcelProperty("髂嵴点高")
    private BigDecimal column_2;

    @ExcelProperty("髂前上棘点高/髂前上棘高")
    private BigDecimal column_3;

    @ExcelProperty("脐高")
    private BigDecimal column_4;

    @ExcelProperty("腰节点高")
    private BigDecimal column_5;

    @ExcelProperty("会阴高")
    private BigDecimal column_6;

    @ExcelProperty("腰节宽")
    private BigDecimal column_7;

    @ExcelProperty("腹宽")
    private BigDecimal column_8;

    @ExcelProperty("腰厚")
    private BigDecimal column_9;

    @ExcelProperty("腹厚")
    private BigDecimal column_10;

    @ExcelProperty("臀宽")
    private BigDecimal column_11;

    @ExcelProperty("大转子点间宽")
    private BigDecimal column_12;

    @ExcelProperty("臀厚")
    private BigDecimal column_13;

    @ExcelProperty("臀膝距")
    private BigDecimal column_14;

    @ExcelProperty("臀腓骨头距")
    private BigDecimal column_15;

    @ExcelProperty("臀大转子点距")
    private BigDecimal column_16;

    @ExcelProperty("臀峰点高")
    private BigDecimal column_17;

    @ExcelProperty("臀沟高")
    private BigDecimal column_18;
}
