package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ForearmRowData extends BodyInfoRow {

    @ExcelProperty("前臂长")
    private BigDecimal column_1; 

    @ExcelProperty("前臂最大宽")
    private BigDecimal column_2; 

    @ExcelProperty("腕关节厚")
    private BigDecimal column_3; 

}
