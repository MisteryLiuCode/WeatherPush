package com.liu.weathermail.enums;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 描述: 状态枚举
 *
 * @author: liushuaibiao
 */
@Getter
@SuppressWarnings("all")
public enum StatusEnum {

    N("0", "否/停用/失效/关闭/删除"),
    Y("1", "是/启用/生效/开启/保存");

    private String code;
    private String desc;

    StatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static List<DictEnumVO> toList() {
        StatusEnum[] values = values();
        List<DictEnumVO> dictList = new ArrayList<>(values.length);
        for (StatusEnum value : values) {
            DictEnumVO vo = new DictEnumVO();
            vo.setCode(value.code);
            vo.setDesc(value.desc);
            dictList.add(vo);
        }
        return dictList;
    }

    public static StatusEnum ofType(String type) {
        return Arrays.stream(values()).filter(v -> v.getCode().equals(type)).findFirst().orElse(null);
    }
}
