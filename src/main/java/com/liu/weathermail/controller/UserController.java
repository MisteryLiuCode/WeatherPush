package com.liu.weathermail.controller;

import com.liu.weathermail.annotation.PrintLog;
import com.liu.weathermail.entity.req.SaveUserInfoReq;
import com.liu.weathermail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

/**
 * All rights Reserved, Designed By misteryliu.
 * 用户相关
 *
 * @author misteryliu@outlook.com
 * @since 2023/11/18 15:46 Copyright ©2023 misteryliu. All rights reserved.
 */

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PrintLog
    @RequestMapping("/saveUserInfo")
    public Boolean saveUserInfo(@RequestBody SaveUserInfoReq saveUserInfoReq){
        return userService.saveUser(saveUserInfoReq);
    }
}
