package com.hc.weathermail.controller;

import cn.hutool.extra.mail.MailUtil;
import com.hc.weathermail.annotation.PrintLog;
import com.hc.weathermail.entity.Daily;
import com.hc.weathermail.entity.TomorrowWeatherVO;
import com.hc.weathermail.entity.WeatherEnum;
import com.hc.weathermail.utils.ConfigUtil;
import com.hc.weathermail.utils.SendSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
        log.info("开始获取今天天气信息");
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = ConfigUtil.getTemplate();
            String resUrl = ConfigUtil.getTomorrowResUrl(weatherConfig);
            String to = weatherConfig.getString("toLsb");
            ResponseEntity<TomorrowWeatherVO> res = restTemplate.getForEntity(resUrl, TomorrowWeatherVO.class);
            List<Daily> dailyList = res.getBody().getDaily();
            if (dailyList != null) {
                // 发送邮件
                StringBuilder sb = new StringBuilder();
                sb.append("今日天气:").append(dailyList.get(0).getTextDay());
                log.info("邮件内容：" + sb.toString());
                MailUtil.send(to, "天气情况", sb.toString(), false);
//                发送短信
//                String toDayWeatherId = weatherConfig.getString("toDayWeatherId");
//                String liuAddressee = weatherConfig.getString("liuAddressee");
//                String[] addressee=new String[]{liuAddressee};
//                String[] args= {dailyList.get(0).getTextDay(),dailyList.get(0).getTempMax()};
//                SendSmsUtil.sendSms(toDayWeatherId,addressee,args);
            }
            return "hello world";
        }
        return "fail";
    }
}
