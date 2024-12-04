package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class HeadRowData extends BodyInfoRow {

    @ExcelProperty("头长")
    private BigDecimal column_1; // 头长

    @ExcelProperty("头宽")
    private BigDecimal column_2; // 头宽

    @ExcelProperty("头高")
    private BigDecimal column_3; // 头高

    @ExcelProperty("面宽")
    private BigDecimal column_4; // 面宽

    @ExcelProperty("两耳屏间宽")
    private BigDecimal column_5; // 两耳屏间宽

    @ExcelProperty("额最小宽")
    private BigDecimal column_6; // 额最小宽

    @ExcelProperty("两眼外宽")
    private BigDecimal column_7; // 两眼外宽

    @ExcelProperty("两眼内宽")
    private BigDecimal column_8; // 两眼内宽

    @ExcelProperty("鼻中宽")
    private BigDecimal column_9; // 鼻中宽

    @ExcelProperty("鼻宽")
    private BigDecimal column_10; // 鼻宽

    @ExcelProperty("口宽")
    private BigDecimal column_11; // 口宽

    @ExcelProperty("口中颏上距")
    private BigDecimal column_12; // 口中颏上距

    @ExcelProperty("两下颌角间宽")
    private BigDecimal column_13; // 两下颌角间宽

    @ExcelProperty("头耳高")
    private BigDecimal column_14; // 头耳高

    @ExcelProperty("形态面长")
    private BigDecimal column_15; // 形态面长

    @ExcelProperty("鼻长")
    private BigDecimal column_16; // 鼻长

    @ExcelProperty("鼻高")
    private BigDecimal column_17; // 鼻高

    @ExcelProperty("形态上面长")
    private BigDecimal column_18; // 形态上面长

    @ExcelProperty("容貌上面长")
    private BigDecimal column_19; // 容貌上面长

    @ExcelProperty("眼高")
    private BigDecimal column_20; // 眼高
}
