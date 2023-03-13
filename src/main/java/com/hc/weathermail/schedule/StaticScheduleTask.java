package com.hc.weathermail.schedule;

import cn.hutool.extra.mail.MailUtil;
import com.hc.weathermail.annotation.PrintLog;
import com.hc.weathermail.entity.*;
import com.hc.weathermail.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Component
@EnableScheduling
@Slf4j
public class StaticScheduleTask {
    /*
    每天早上7点55执行，如果未来14个小时内下雨，也就是到晚上11点，则推送几点下雨
     */
    @PrintLog
    @Scheduled(cron = "0 55 07 * * ?")
    public void heFengHourWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            log.info("开始执行定时任务,查询早上8点到晚上11点是否下雨");
            RestTemplate restTemplate = ConfigUtil.getTemplate();
            String resUrl = ConfigUtil.getHourResUrl(weatherConfig);
            String to = weatherConfig.getString("toZpr");
            ResponseEntity<HeFengWeatherHourVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherHourVO.class);
            List<Hourly> hourlyList = res.getBody().getHourly();
            if (hourlyList != null) {
                List<Hourly> newHourList = hourlyList.subList(0, 15);
                for (Hourly hourly : newHourList) {
                    if (hourly.getText().contains(WeatherEnum.B.getCode())) {
                        // 发送邮件
                        StringBuilder sb = new StringBuilder();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                        sb.append("预计").append(simpleDateFormat.format(hourly.getFxTime())).append("点开始下雨，")
                                .append("出门记得带伞：");
                        log.info("邮件内容：" + sb.toString());
                        MailUtil.send(to, "天气情况", sb.toString(), false);
                        break;
                    }
                }
            }
        }
    }
    //晚上6点到明天晚上6点如果有雨,提醒下班带伞
    @PrintLog
    @Scheduled(cron = "0 30 17 * * ?")
    public void heFengWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            log.info("开始执行定时任务,查询下午6点到明天晚上6点是否有雨");
            RestTemplate restTemplate = ConfigUtil.getTemplate();
            String resUrl = ConfigUtil.getHourResUrl(weatherConfig);
            String to = weatherConfig.getString("toZpr");
            //获取当前时间
            SimpleDateFormat nowSdf = new SimpleDateFormat("dd");// 格式化时间
            String nowDay = nowSdf.format(new Date());
            ResponseEntity<HeFengWeatherHourVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherHourVO.class);
            List<Hourly> hourlyList = res.getBody().getHourly();
            if (hourlyList != null) {
                for (Hourly hourly : hourlyList) {
                    if (hourly.getText().contains(WeatherEnum.B.getCode())) {
                        // 发送邮件
                        StringBuilder sb = new StringBuilder();
                        SimpleDateFormat hourDateFormat = new SimpleDateFormat("HH");
                        //下雨时间与当前时间比较
                        String rainDay = nowSdf.format(hourly.getFxTime());
                        String rain = rainDay.equals(nowDay) ? "今天" : "明天";
                        sb.append("预计").append(rain).append(hourDateFormat.format(hourly.getFxTime())).append("点开始下雨，")
                                .append("下班记得带伞");
                        log.info("邮件内容：" + sb);
                        MailUtil.send(to, "天气情况", sb.toString(), false);
                        break;
                    }
                }
            }
        }
    }
}
