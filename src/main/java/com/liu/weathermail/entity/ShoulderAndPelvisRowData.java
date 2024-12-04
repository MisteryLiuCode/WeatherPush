package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ShoulderAndPelvisRowData extends BodyInfoRow {

    @ExcelProperty("骶岬角")
    private BigDecimal column_1;

    @ExcelProperty("舟骨最大长")
    private BigDecimal column_2;

    @ExcelProperty("月骨最大长")
    private BigDecimal column_3;

    @ExcelProperty("肩胛总高")
    private BigDecimal column_4;

    @ExcelProperty("肩胛宽")
    private BigDecimal column_5;

    @ExcelProperty("肩胛长")
    private BigDecimal column_6;

    @ExcelProperty("腋缘长")
    private BigDecimal column_7;

    @ExcelProperty("肩峰宽")
    private BigDecimal column_8;

    @ExcelProperty("肩峰长")
    private BigDecimal column_9;

    @ExcelProperty("关节盂长")
    private BigDecimal column_10;

    @ExcelProperty("喙突长")
    private BigDecimal column_11;

    @ExcelProperty("最大长")
    private BigDecimal column_12;

    @ExcelProperty("上端宽")
    private BigDecimal column_13;

    @ExcelProperty("下端宽")
    private BigDecimal column_14;

    @ExcelProperty("体中部最大径")
    private BigDecimal column_15;

}
