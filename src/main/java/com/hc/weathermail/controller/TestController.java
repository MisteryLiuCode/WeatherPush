package com.hc.weathermail.controller;

import com.hc.weathermail.annotation.PrintLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: houcheng
 * @date: 2021/7/21 11:16
 * @version: V1.0
 * @description: 测试程序是否启动
 * @modify:
 */
@RestController
public class TestController {

    @PrintLog
    @GetMapping("/hello/{id}")
    public String hello(@PathVariable String id) {
        return "hello world!";
    }
}
