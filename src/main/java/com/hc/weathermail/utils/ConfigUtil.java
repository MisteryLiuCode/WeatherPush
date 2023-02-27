package com.hc.weathermail.utils;


import cn.hutool.core.util.PageUtil;
import com.hc.weathermail.entity.ProjectConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Component;

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

    /**
     * 获取系统配置信息
     * @return 配置信息
     */
    public static Configuration getWeatherConfig() {
        if (configuration == null) {
            try {
                configuration = new PropertiesConfiguration(ProjectConstant.WEATHERMAIL_CONFIG);
            } catch (ConfigurationException e) {
                log.error("获取配置文件异常！" + e.getMessage());
                return null;
            }
        }
        return configuration;
    }

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
}