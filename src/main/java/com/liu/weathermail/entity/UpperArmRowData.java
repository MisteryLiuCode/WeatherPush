package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class UpperArmRowData extends BodyInfoRow {

    @ExcelProperty("肘高")
    private BigDecimal column_1; 

    @ExcelProperty("桡骨茎突点高")
    private BigDecimal column_2; 

    @ExcelProperty("桡骨点高/桡骨头高")
    private BigDecimal column_3; 

    @ExcelProperty("上臂长")
    private BigDecimal column_4; 

    @ExcelProperty("上臂根厚")
    private BigDecimal column_5; 

    @ExcelProperty("两肘间宽")
    private BigDecimal column_6; 

    @ExcelProperty("全臂长")
    private BigDecimal column_7; 

    @ExcelProperty("上肢长")
    private BigDecimal column_8; 

}
