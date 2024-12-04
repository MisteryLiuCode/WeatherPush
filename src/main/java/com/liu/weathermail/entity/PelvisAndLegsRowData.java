package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class PelvisAndLegsRowData extends BodyInfoRow {

    @ExcelProperty("尾骨弧")
    private BigDecimal column_1;

    @ExcelProperty("胫骨长")
    private BigDecimal column_2;

    @ExcelProperty("髁踝长")
    private BigDecimal column_3;

    @ExcelProperty("根骨全长")
    private BigDecimal column_4;

    @ExcelProperty("根骨中部宽")
    private BigDecimal column_5;

    @ExcelProperty("根骨高")
    private BigDecimal column_6;

    @ExcelProperty("距骨长")
    private BigDecimal column_7;

    @ExcelProperty("距骨宽")
    private BigDecimal column_8;

    @ExcelProperty("距骨高")
    private BigDecimal column_9;

    @ExcelProperty("足舟骨宽")
    private BigDecimal column_10;

    @ExcelProperty("足舟骨高")
    private BigDecimal column_11;

    @ExcelProperty("骨盆高")
    private BigDecimal column_12;

    @ExcelProperty("髋骨最大宽")
    private BigDecimal column_13;

    @ExcelProperty("骨盆最大宽")
    private BigDecimal column_14;

    @ExcelProperty("坐骨高")
    private BigDecimal column_15;

    @ExcelProperty("耻骨长")
    private BigDecimal column_16;

}
