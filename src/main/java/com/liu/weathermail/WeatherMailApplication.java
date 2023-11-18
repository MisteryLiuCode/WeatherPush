package com.liu.weathermail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author houcheng
 */
@SpringBootApplication(scanBasePackages = "com.liu.weathermail")
public class WeatherMailApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherMailApplication.class, args);
    }

}
