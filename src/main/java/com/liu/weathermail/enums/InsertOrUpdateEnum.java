package com.liu.weathermail.enums;

import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * All rights Reserved, Designed By misteryliu.
 * 新增或者修改枚举
 *
 * @author misteryliu@outlook.com
 * @since 2023/12/30 16:28 Copyright ©2023 misteryliu. All rights reserved.
 */

@Getter
public enum InsertOrUpdateEnum {

    INSERT("1", "添加"),
    UPDATE("2", "修改");

    private String code;
    private String desc;

    InsertOrUpdateEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
