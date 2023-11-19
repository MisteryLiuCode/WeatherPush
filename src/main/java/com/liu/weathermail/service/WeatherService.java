package com.liu.weathermail.service;

import cn.hutool.extra.mail.MailUtil;
import com.liu.weathermail.entity.*;
import com.liu.weathermail.entity.req.WeatherReq;
import com.liu.weathermail.utils.ConfigUtil;
import com.liu.weathermail.utils.SendSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * All rights Reserved, Designed By misteryliu. 查询天气
 *
 * @author xupengfei@jiexingcloud.com
 * @since 2023/11/19 14:41 Copyright ©2023 jiexingcloud. All rights reserved.
 */
public interface WeatherService {

    Boolean getWeather(WeatherReq req);
}
