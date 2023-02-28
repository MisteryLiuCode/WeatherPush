package com.hc.weathermail.controller;

import cn.hutool.extra.mail.MailUtil;
import com.hc.weathermail.annotation.PrintLog;
import com.hc.weathermail.entity.Daily;
import com.hc.weathermail.entity.TomorrowWeatherVO;
import com.hc.weathermail.entity.WeatherEnum;
import com.hc.weathermail.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author: houcheng
 * @date: 2021/7/21 11:16
 * @version: V1.0
 * @description: 测试程序是否启动
 * @modify:
 */
@RestController
@Slf4j
public class TestController {


    /**
     * description: 测试天气
     *
     * @param
     * @return java.lang.String
     */
    @PrintLog
    @GetMapping("/hello")
    public String heFengTomorrowWeather() {
        log.info("开始获取明天天气信息");
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = ConfigUtil.getTemplate();
            String resUrl = ConfigUtil.getTomorrowHourResUrl(weatherConfig);
            String to = weatherConfig.getString("toLsb");
            ResponseEntity<TomorrowWeatherVO> res = restTemplate.getForEntity(resUrl, TomorrowWeatherVO.class);
            List<Daily> dailyList = res.getBody().getDaily();
            if (dailyList != null) {
                // 发送邮件
                StringBuilder sb = new StringBuilder();
                sb.append("今日天气:").append(dailyList.get(0).getTextDay());
                log.info("邮件内容：" + sb.toString());
                MailUtil.send(to, "天气情况", sb.toString(), false);
            }
            return "hello world";
        }
        return "fail";
    }
}
