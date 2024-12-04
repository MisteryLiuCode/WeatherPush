package com.liu.weathermail.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 个人信息实体类
 */
@Data
@TableName("person_info")
public class PersonInfoEntity {
    /** 编号 */
    private String id;

    /** 姓名 */
    private String name;

    /** 性别（男1女0） */
    private Long sex;

    /** 年龄 */
    private Long age;

    /** 身高（毫米） */
    private BigDecimal height;

    /** 体重（kg） */
    private BigDecimal weight;

    /** 籍贯 */
    private String address;

    /** 出生日期 */
    private Date birthday;

    /** 职业 */
    private String career;

    /** 民族 */
    private String nation;

    /** 坐高 */
    private BigDecimal zg;

    /** 编号 */
    private String bh;

    /** 区域 */
    private String qy;
}
