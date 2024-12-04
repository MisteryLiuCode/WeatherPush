package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class NickRowData extends BodyInfoRow{

    @ExcelProperty("颈根高")
    private BigDecimal column_1; // 头长

    @ExcelProperty("颈椎点高")
    private BigDecimal column_2; // 头宽

    @ExcelProperty("颈宽")
    private BigDecimal column_3; // 头高

    @ExcelProperty("颈根宽")
    private BigDecimal column_4; // 面宽

    @ExcelProperty("颈厚")
    private BigDecimal column_5; // 两耳屏间宽
}
