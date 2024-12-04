package com.liu.weathermail.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class SkullRowData extends BodyInfoRow {

    @ExcelProperty("颅骨最大长")
    private BigDecimal column_1; 

    @ExcelProperty("颅底长")
    private BigDecimal column_2; 

    @ExcelProperty("枕骨大孔长")
    private BigDecimal column_3; 

    @ExcelProperty("面底长")
    private BigDecimal column_4; 

    @ExcelProperty("下面长")
    private BigDecimal column_5; 

    @ExcelProperty("颅最大宽")
    private BigDecimal column_6; 

    @ExcelProperty("颅最小宽")
    private BigDecimal column_7; 

    @ExcelProperty("枕骨大孔宽")
    private BigDecimal column_8; 

    @ExcelProperty("枕骨底部宽")
    private BigDecimal column_9; 

    @ExcelProperty("最小额宽")
    private BigDecimal column_10; 

    @ExcelProperty("颅高")
    private BigDecimal column_11; 

    @ExcelProperty("全颅高")
    private BigDecimal column_12; 

    @ExcelProperty("颅盖高")
    private BigDecimal column_13; 

    @ExcelProperty("耳上颅高")
    private BigDecimal column_14; 

    @ExcelProperty("颧骨矢高")
    private BigDecimal column_15; 

    @ExcelProperty("颧上颌高")
    private BigDecimal column_16; 

    @ExcelProperty("上面高")
    private BigDecimal column_17; 

    @ExcelProperty("下颌体高")
    private BigDecimal column_18; 

    @ExcelProperty("颅周长")
    private BigDecimal column_19; 

    @ExcelProperty("前囱角")
    private BigDecimal column_20; 

    @ExcelProperty("顶角")
    private BigDecimal column_21; 

    @ExcelProperty("枕角")
    private BigDecimal column_22; 

}
