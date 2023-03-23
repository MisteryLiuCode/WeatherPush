package com.hc.weathermail.utils;


import cn.hutool.core.util.PageUtil;
import com.hc.weathermail.entity.ProjectConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author: houcheng
 * @date: 2021/7/24 16:03
 * @version: V1.0
 * @description: 读取配置文件信息
 * @modify:
 */
@Slf4j
public class ConfigUtil {

    private static Configuration configuration;

    public static Configuration getHeFengWeatherConfig() {
        if (configuration == null) {
            try {
                configuration = new PropertiesConfiguration(ProjectConstant.HEFENGWEATHERMAIL_CONFIG);
            } catch (ConfigurationException e) {
                log.error("获取配置文件异常！" + e.getMessage());
                return null;
            }
        }
        return configuration;
    }

    public static RestTemplate getTemplate() {
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
    public static String getHourResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("HourUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

    public static String getTomorrowResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("TomorrowUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

    public static String getWarningUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("warningUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 使用restTemplate发送请求
//        RestTemplate restTemplate = new RestTemplate();
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "lang=zh" +
                "&" + "key=" + key;
        return resUrl;
    }


    /**
     * description: 每日天气url
     *
     * @param weatherConfig
     * @return java.lang.String
     */
    public static String getDayResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("DayUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }
}