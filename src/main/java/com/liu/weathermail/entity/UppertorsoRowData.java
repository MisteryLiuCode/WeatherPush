package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class UppertorsoRowData extends BodyInfoRow {

    @ExcelProperty("肩高")
    private BigDecimal column_1;

    @ExcelProperty("肩端点高II")
    private BigDecimal column_2;

    @ExcelProperty("胸中点高")
    private BigDecimal column_3;

    @ExcelProperty("乳头高")
    private BigDecimal column_4;

    @ExcelProperty("乳房下缘高")
    private BigDecimal column_5;

    @ExcelProperty("肩胛下角高")
    private BigDecimal column_6;

    @ExcelProperty("胸上点高")
    private BigDecimal column_7;

    @ExcelProperty("锁骨点高")
    private BigDecimal column_8;

    @ExcelProperty("肩端点高")
    private BigDecimal column_9;

    @ExcelProperty("肩宽")
    private BigDecimal column_10;

    @ExcelProperty("肩端宽")
    private BigDecimal column_11;

    @ExcelProperty("肩峰肘距")
    private BigDecimal column_12;

    @ExcelProperty("肩最大宽")
    private BigDecimal column_13;

    @ExcelProperty("胸厚")
    private BigDecimal column_14;

    @ExcelProperty("胸宽")
    private BigDecimal column_15;

    @ExcelProperty("腋窝前宽")
    private BigDecimal column_16;

    @ExcelProperty("腋窝后宽")
    private BigDecimal column_17;

    @ExcelProperty("乳头间宽")
    private BigDecimal column_18;

    @ExcelProperty("上胸深")
    private BigDecimal column_19;

    @ExcelProperty("腋窝前宽II")
    private BigDecimal column_20;

    @ExcelProperty("腋窝前宽III")
    private BigDecimal column_21;

    @ExcelProperty("腋窝后宽II")
    private BigDecimal column_22;

    @ExcelProperty("腋窝后宽III")
    private BigDecimal column_23;

    @ExcelProperty("胸中宽/胸宽/胸宽I")
    private BigDecimal column_24;

    @ExcelProperty("胸中部矢状径")
    private BigDecimal column_25;

    @ExcelProperty("胸矢状径")
    private BigDecimal column_26;

    @ExcelProperty("背肩峰距")
    private BigDecimal column_27;
}
