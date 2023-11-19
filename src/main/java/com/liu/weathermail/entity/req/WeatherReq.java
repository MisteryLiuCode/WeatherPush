package com.liu.weathermail.entity.req;

import lombok.Data;

/**
 * All rights Reserved, Designed By misteryliu.
 * 查询天气入参
 *
 * @author misteryliu@outlook.com
 * @since 2023/11/19 14:42 Copyright ©2023 misteryliu. All rights reserved.
 */
@Data
public class WeatherReq {

    private String sendEmail;

    private String sendPassword;

    private String recName;

    private String recEmail;

    private String cityCode;

    private String recPhone;

    private String recId;

}
