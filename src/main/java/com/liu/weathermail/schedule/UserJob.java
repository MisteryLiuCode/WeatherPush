package com.liu.weathermail.schedule;

import com.liu.weathermail.dao.SendUserDao;
import com.liu.weathermail.entity.po.UserInfoPO;

import javax.annotation.Resource;
import java.util.List;
import org.springframework.util.CollectionUtils;

/**
 * All rights Reserved, Designed By misteryliu.
 * 定时查询用户
 *
 * @author misteryliu@outlook.com
 * @since 2023/11/19 14:15 Copyright ©2023 misteryliu. All rights reserved.
 */
public class UserJob {

    @Resource
    private SendUserDao sendUserDao;

    /**
     * 一分钟查一次用户，如果有发送时间等于此时的话，调用查询天气方法
     *
     * @param
     * @return void
     * @since 2023/11/19 13:54 by misteryliu
     **/
    public void getUserInfo(){
        List<UserInfoPO> userInfoPOS = sendUserDao.selectUserInfo();
        if (!CollectionUtils.isEmpty(userInfoPOS)){

        }


    }
}
