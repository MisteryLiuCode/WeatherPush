package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class FootRowData extends BodyInfoRow {

    @ExcelProperty("足长")
    private BigDecimal column_1; 

    @ExcelProperty("足宽")
    private BigDecimal column_2; 

    @ExcelProperty("足后跟宽")
    private BigDecimal column_3; 

    @ExcelProperty("足背高")
    private BigDecimal column_4; 

    @ExcelProperty("足面长")
    private BigDecimal column_5; 

    @ExcelProperty("足后跟胫侧踮骨点距")
    private BigDecimal column_6; 

    @ExcelProperty("足趾高")
    private BigDecimal column_7; 

}
