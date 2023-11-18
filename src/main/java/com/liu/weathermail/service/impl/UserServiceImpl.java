package com.liu.weathermail.service.impl;

import com.liu.weathermail.dao.RecUserDao;
import com.liu.weathermail.dao.SendUserDao;
import com.liu.weathermail.entity.RecUserEntity;
import com.liu.weathermail.entity.SendUserEntity;
import com.liu.weathermail.entity.req.SaveUserInfoReq;
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

    @Override

    public Boolean saveUser(SaveUserInfoReq req) {
        SendUserEntity sendUserEntity = new SendUserEntity();
        sendUserEntity.setId(UUID.randomUUID().toString());
        sendUserEntity.setSendMail(req.getSendMail());
        sendUserEntity.setSendPassword(req.getSendMail());
        sendUserEntity.setNickName(req.getSendMail());
        sendUserEntity.setCreateTime(new Date());
        sendUserEntity.setUpdateTime(new Date());
        int insertSend = sendUserDao.insert(sendUserEntity);

        RecUserEntity recUserEntity = new RecUserEntity();
        recUserEntity.setSendId(sendUserEntity.getId());
        recUserEntity.setRecEmail(req.getRecEmail());
        recUserEntity.setRecPhone(req.getRecPhone());
        recUserEntity.setCityCode(req.getCityCode());
        recUserEntity.setCityName(req.getCityName());
        recUserEntity.setCreateTime(new Date());
        recUserEntity.setUpdateTime(new Date());
        int insertRec = recUserDao.insert(recUserEntity);
        return insertSend == 1 && insertRec == 1;
    }
}