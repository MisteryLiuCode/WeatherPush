package com.hc.weathermail;

import cn.hutool.json.JSON;
import com.hc.weathermail.entity.*;
import com.hc.weathermail.utils.ConfigUtil;
import org.apache.commons.configuration.Configuration;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;


@SpringBootTest
class WeatherMailApplicationTests {

    /**
     * 测试获取天气信息
     */
    @Test
    public void testGetWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
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
            System.out.println(res.getBody().getNow());
        }

    }


    /**
     * 测试获取当天天气信息
     */
    @Test
    public void testHeFengWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String resUrl = getDayResUrl(weatherConfig);

            ResponseEntity<HeFengWeatherDayVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherDayVO.class);
            HeFengWeatherDayVO weatherVo = res.getBody();
            System.out.println(weatherVo);
        }
    }

    /**
     * 测试逐小时获取当天天气信息
     */
    @Test
    public void testHeFengHourWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String resUrl = getHourResUrl(weatherConfig);
            ResponseEntity<HeFengWeatherHourVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherHourVO.class);
            List<Hourly> hourlyList = res.getBody().getHourly();
            Hourly resHourly = new Hourly();
            //获取8点到11点的天气数据
            if (hourlyList!=null){
                List<Hourly> newHourList = hourlyList.subList(0, 15);
                for (Hourly hourly : newHourList) {
                    if (hourly.getText().equals(WeatherEnum.B.getCode())){
                        resHourly=hourly;
                        System.out.println("下雨天气："+hourly);
                        break;
                    }
                }
            }
            System.out.println(resHourly);
        }
    }

    private RestTemplate getTemplate(){
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
        return restTemplate;
    }

    /**
     * description: 逐小时天气url
     *
     * @param weatherConfig
     * @return java.lang.String
     */
    private String getHourResUrl(Configuration weatherConfig){
        String url = weatherConfig.getString("HourUrl");
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
    private String getDayResUrl(Configuration weatherConfig){
        String url = weatherConfig.getString("DayUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

}
