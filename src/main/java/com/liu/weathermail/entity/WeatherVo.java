package com.liu.weathermail.entity;

import lombok.Data;

/**
 * @author: houcheng
 * @date: 2021/7/23 9:02
 * @version: V1.0
 * @description:
 * @modify:
 */
@Data
public class WeatherVo {

    private String code;
    /**
     * 城市名称
     */
    private String city;
    private String date;
    /**
     * 当前星期
     */
    private String week;
    /**
     * 气象台更新时间
     */
    private String update_time;

    /**
     * 天气情况
     */
    private String wea;

    /**
     * 实时温度
     */
    private String tem;

    /**
     * 最高温
     */
    private String tem1;
    /**
     * 最低温
     */
    private String tem2;

    /**
     * 风力等级，大于等于6是强风
     */
    private String win_speed;

    /**
     * 今日建议
     */
    private String air_tips;
}
