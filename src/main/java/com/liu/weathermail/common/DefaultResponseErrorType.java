package com.liu.weathermail.common;

/**
 * All rights Reserved, Designed By misteryliu.
 *
 * @author misteryliu@outlook.com
 * @since 2023/12/24 16:21 Copyright ©2023 misteryliu. All rights reserved.
 */
public enum DefaultResponseErrorType implements IResponseErrorType {
    SUCCESS("0000", "操作成功"),
    ERROR("9999", "系统异常,请稍后重试!");

    private final String code;
    private final String message;

    private DefaultResponseErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
