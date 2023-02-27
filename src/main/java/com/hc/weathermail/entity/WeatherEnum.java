package com.hc.weathermail.entity;

/**
 * @author liushuaibiao
 * @date 2023/2/27 11:17
 */
public enum WeatherEnum {
    
    /***
     * description: 
     * 
     * @param null 
     * @return
     */
    A("晴", "晴天"),
    B("雨", "雨天"),
    ;

    private String code;

    private String remark;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    WeatherEnum(String code, String remark) {
        this.code = code;
        this.remark = remark;
    }
}
