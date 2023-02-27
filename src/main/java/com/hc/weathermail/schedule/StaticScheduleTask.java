package com.hc.weathermail.schedule;

import cn.hutool.extra.mail.MailUtil;
import com.hc.weathermail.entity.HeFengWeatherDayVO;
import com.hc.weathermail.entity.Now;
import com.hc.weathermail.entity.WeatherVo;
import com.hc.weathermail.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
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

import java.time.LocalDateTime;

/**
 * @author: houcheng
 * @date: 2021/7/22 14:34
 * @version: V1.0
 * @description:
 * @modify:
 */
@Component
@EnableScheduling
@Slf4j
public class StaticScheduleTask {

    /**
     * 每天12点和18点执行
     */
//    @Scheduled(cron = "0 0 12,18 * * ?")
    @Scheduled(cron = "*/5 * * * * ?")
    public void sendWeatherMail() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            String to = weatherConfig.getString("toLsb");
            // 获取天气信息
            Now weatherInfo = getWeatherInfo(weatherConfig);
            if (weatherInfo != null) {
                log.info("获取天气信息：" + weatherInfo.toString());
                // 发送邮件
                StringBuilder sb = new StringBuilder();
                sb.append("当前天气：").append(weatherInfo.getText()).append("\n")
                        .append("当前温度：").append(weatherInfo.getTemp()).append("\n")
                        .append("当前风向：").append(weatherInfo.getWindDir()).append("\n")
                        .append("风力等级:").append(weatherInfo.getWindScale()).append("级").append("\n")
                        ;
                log.info("邮件内容：" + sb.toString());
                MailUtil.send(to, "天气情况", sb.toString(), false);
            } else {
                MailUtil.send(to, "获取天气情况失败", "应该是接口出问题了", false);
            }
        }
    }

    /**
     * 获取天气信息
     * @param weatherConfig 配置信息
     * @return WeatherVo
     */
    private Now getWeatherInfo(Configuration weatherConfig) {
        // 添加拦截器，使用 gzip 编码提交
        ClientHttpRequestInterceptor interceptor = (httpRequest, bytes, execution) -> {
            httpRequest.getHeaders().set("user-agent",   "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
            httpRequest.getHeaders().set(HttpHeaders.ACCEPT,  MediaType.APPLICATION_JSON_VALUE);
            httpRequest.getHeaders().set(HttpHeaders.ACCEPT_ENCODING, "gzip");   // 使用 gzip 编码提交
            return execution.execute(httpRequest, bytes);
        };
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getInterceptors().add(interceptor);

        String url = weatherConfig.getString("DayUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 使用restTemplate发送请求
//        RestTemplate restTemplate = new RestTemplate();
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        ResponseEntity<HeFengWeatherDayVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherDayVO.class);
        return res.getBody().getNow();
    }



}
