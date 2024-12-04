package com.liu.weathermail.entity;

import lombok.Data;

@Data
public class SysDictEntity {

    private Long id; // 主键
    private String type; // 字典类型
    private String dictCode; // 字典编码
    private String dictName; // 字典名
    private String affiliation; // 所属功能或页面，多页面以θ分割
}
