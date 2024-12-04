package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class ThighRowData extends BodyInfoRow {

    @ExcelProperty("大腿长")
    private BigDecimal column_1; 

    @ExcelProperty("大腿厚")
    private BigDecimal column_2; 

    @ExcelProperty("大腿宽")
    private BigDecimal column_3; 

    @ExcelProperty("大转子点高")
    private BigDecimal column_4; 

}