package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class TotalRowData {
    @ExcelProperty("编号")
    private Integer bh;

    @ExcelProperty("性别")
    private String excelGender;

    @ExcelProperty("年龄")
    private Long age;

    @ExcelProperty("身高")
    private BigDecimal height;

    @ExcelProperty("体重")
    private BigDecimal weight;

    @ExcelProperty("区域")
    private String qy;

    @ExcelProperty("籍贯")
    private String address;
}
