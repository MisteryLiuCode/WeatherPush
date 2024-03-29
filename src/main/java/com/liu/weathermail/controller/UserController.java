package com.liu.weathermail.controller;

import com.liu.weathermail.annotation.PrintLog;
import com.liu.weathermail.entity.req.SaveRecUserInfoReq;
import com.liu.weathermail.entity.req.SaveSendUserInfoReq;
import com.liu.weathermail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @RequestMapping("/saveSendUserInfo")
    public Boolean saveUserInfo(@RequestBody SaveSendUserInfoReq req){
        return userService.saveSendUser(req);
    }

    @PrintLog
    @RequestMapping("/saveRecUserInfo")
    public Boolean saveRecUser(@RequestBody SaveRecUserInfoReq req){
        return userService.saveRecUser(req);
    }
}
