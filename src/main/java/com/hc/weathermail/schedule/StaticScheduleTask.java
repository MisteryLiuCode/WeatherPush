package com.hc.weathermail.schedule;

import cn.hutool.extra.mail.MailUtil;
import com.hc.weathermail.entity.*;
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

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;


@Component
@EnableScheduling
@Slf4j
public class StaticScheduleTask {
    /*
    每天早上7点55执行，如果未来14个小时内下雨，则推送几点下雨
     */
    @Scheduled(cron = "0 55 70 * * ?")
    public void heFengHourWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String resUrl = getHourResUrl(weatherConfig);
            String to = weatherConfig.getString("toZpr");
            ResponseEntity<HeFengWeatherHourVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherHourVO.class);
            List<Hourly> hourlyList = res.getBody().getHourly();
            //获取8点到11点的天气数据
            if (hourlyList != null) {
                List<Hourly> newHourList = hourlyList.subList(0, 15);
                for (Hourly hourly : newHourList) {
                    if (hourly.getText().equals(WeatherEnum.B.getCode())) {
                        // 发送邮件
                        StringBuilder sb = new StringBuilder();
//                        SimpleDateFormat df_12=new SimpleDateFormat("hh");
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                        sb.append("预计").append(simpleDateFormat.format(hourly.getFxTime())).append("点会下雨，")
                                .append("出门记得带伞：");
                        log.info("邮件内容：" + sb.toString());
                        MailUtil.send(to, "天气情况", sb.toString(), false);
                        break;
                    }
                }
            }
        }
    }

    //下班前20分钟如果还在下雨或者明天有雨，推送消息，今天和明天都不下雨不推送。
    @Scheduled(cron = "0 30 17 * * ?")
    public void heFengWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String resUrl = getHourResUrl(weatherConfig);
            String to = weatherConfig.getString("toZpr");
            ResponseEntity<HeFengWeatherHourVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherHourVO.class);
            List<Hourly> hourlyList = res.getBody().getHourly();
            //获取8点到11点的天气数据
            if (hourlyList != null) {
                List<Hourly> newHourList = hourlyList.subList(0, 15);
                for (Hourly hourly : newHourList) {
                    if (hourly.getText().equals(WeatherEnum.B.getCode())) {
                        // 发送邮件
                        StringBuilder sb = new StringBuilder();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                        sb.append("预计").append(simpleDateFormat.format(hourly.getFxTime())).append("点会下雨，")
                                .append("记得带伞：");
                        log.info("邮件内容：" + sb.toString());
                        MailUtil.send(to, "天气情况", sb.toString(), false);
                        break;
                    }
                }
            }
        }
    }


    @Scheduled(cron = "0 30 17 * * ?")
    public void heFengTomorrowWeather() {
        log.info("开始获取明天天气信息");
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String resUrl = getTomorrowHourResUrl(weatherConfig);
            String to = weatherConfig.getString("toZpr");
            ResponseEntity<TomorrowWeatherVO> res = restTemplate.getForEntity(resUrl, TomorrowWeatherVO.class);
            List<Daily> dailyList = res.getBody().getDaily();
            if (dailyList != null) {
                if (dailyList.get(0).getTextDay().equals(WeatherEnum.B.getCode())) {
                    // 发送邮件
                    StringBuilder sb = new StringBuilder();
                    sb.append("预计明天会下雨，记得下班把伞带回去");
                    log.info("邮件内容：" + sb.toString());
                    MailUtil.send(to, "天气情况", sb.toString(), false);
                }
            }
        }
    }


    private RestTemplate getTemplate() {
        // 添加拦截器，使用 gzip 编码提交
        ClientHttpRequestInterceptor interceptor = (httpRequest, bytes, execution) -> {
            httpRequest.getHeaders().set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
            httpRequest.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            httpRequest.getHeaders().set(HttpHeaders.ACCEPT_ENCODING, "gzip");   // 使用 gzip 编码提交
            return execution.execute(httpRequest, bytes);
        };
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getInterceptors().add(interceptor);
        return restTemplate;
    }

    /**
     * description: 逐小时天气url
     *
     * @param weatherConfig
     * @return java.lang.String
     */
    private String getHourResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("HourUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("BeiJingCityid");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

    private String getTomorrowHourResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("TomorrowUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("BeiJingCityid");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

    /**
     * description: 每日天气url
     *
     * @param weatherConfig
     * @return java.lang.String
     */
    private String getDayResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("DayUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

}
