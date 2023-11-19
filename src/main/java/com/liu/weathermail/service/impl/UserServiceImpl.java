package com.liu.weathermail.service.impl;

import com.liu.weathermail.dao.RecUserDao;
import com.liu.weathermail.dao.SendUserDao;
import com.liu.weathermail.entity.RecUserEntity;
import com.liu.weathermail.entity.SendUserEntity;
import com.liu.weathermail.entity.req.SaveUserInfoReq;
import com.liu.weathermail.enums.StatusEnum;
import com.liu.weathermail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * All rights Reserved, Designed By misteryliu.
 *
 * @author misteryliu@outlook.com
 * @since 2023/11/18 17:38 Copyright ©2023 misteryliu. All rights reserved.
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private SendUserDao sendUserDao;

    @Resource
    private RecUserDao recUserDao;

    @Resource
    private WeatherServiceImpl weatherService;

    @Override

    public Boolean saveUser(SaveUserInfoReq req) {
        SendUserEntity sendUserEntity = new SendUserEntity();
        sendUserEntity.setSendMail(req.getSendMail());
        sendUserEntity.setSendPassword(req.getSendMail());
        sendUserEntity.setCreateTime(new Date());
        sendUserEntity.setUpdateTime(new Date());
        sendUserEntity.setStatus(StatusEnum.Y.getCode());
        int insertSend = sendUserDao.insert(sendUserEntity);

        RecUserEntity recUserEntity = new RecUserEntity();
        recUserEntity.setSendId(sendUserEntity.getId());
        recUserEntity.setRecEmail(req.getRecEmail());
        recUserEntity.setRecPhone(req.getRecPhone());
        recUserEntity.setCityCode(req.getCityCode());
        recUserEntity.setCityName(req.getCityName());
        recUserEntity.setRecTime(req.getRecTime());
        recUserEntity.setCreateTime(new Date());
        recUserEntity.setUpdateTime(new Date());
        recUserEntity.setStatus(StatusEnum.Y.getCode());
        int insertRec = recUserDao.insert(recUserEntity);

        weatherService.saveTodayWeather(sendUserEntity.getId(),req.getCityCode());
        return insertSend == 1 && insertRec == 1;
    }
}
