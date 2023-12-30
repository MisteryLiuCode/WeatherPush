package com.liu.weathermail.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.weathermail.dao.RecUserDao;
import com.liu.weathermail.dao.SendUserDao;
import com.liu.weathermail.entity.RecUserEntity;
import com.liu.weathermail.entity.SendUserEntity;
import com.liu.weathermail.entity.req.SaveRecUserInfoReq;
import com.liu.weathermail.entity.req.SaveSendUserInfoReq;
import com.liu.weathermail.enums.InsertOrUpdateEnum;
import com.liu.weathermail.enums.StatusEnum;
import com.liu.weathermail.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

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
    public Boolean saveSendUser(SaveSendUserInfoReq req) {
        SendUserEntity sendUserEntity = new SendUserEntity();
        sendUserEntity.setSendMail(req.getSendMail());
        sendUserEntity.setSendPassword(req.getSendMail());
        sendUserEntity.setCreateTime(new Date());
        sendUserEntity.setUpdateTime(new Date());
        sendUserEntity.setStatus(StatusEnum.Y.getCode());
        int insertSend = sendUserDao.insert(sendUserEntity);

        return insertSend == 1;
    }

    @Override
    public Boolean saveRecUser(SaveRecUserInfoReq req) {
        RecUserEntity recUserEntity = new RecUserEntity();
        BeanUtils.copyProperties(req,recUserEntity);
        if (req.getInsertOrUpdate().equals(InsertOrUpdateEnum.INSERT.getCode())){
            recUserEntity.setCreateTime(new Date());
            recUserEntity.setUpdateTime(new Date());
            recUserEntity.setStatus(StatusEnum.Y.getCode());
            weatherService.saveTodayWeather(recUserEntity.getId(),req.getCityCode());
            int insertRec = recUserDao.insert(recUserEntity);
            return insertRec == 1;
        }else {
            recUserEntity.setUpdateTime(new Date());
            QueryWrapper<RecUserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper
                    .select("id")
                    .eq("recEmail", req.getRecEmail());//name包括i字母
            RecUserEntity recUser = recUserDao.selectOne(queryWrapper);
            recUserEntity.setId(recUser.getId());
            int updateRec = recUserDao.updateById(recUserEntity);
            return updateRec == 1;
        }
    }
}
