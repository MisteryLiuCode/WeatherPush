package com.liu.weathermail.entity.po;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * All rights Reserved, Designed By misteryliu.
 * 发送人和接收人详情
 *
 * @author misteryliu@outlook.com
 * @since 2023/11/19 14:19 Copyright ©2023 misteryliu. All rights reserved.
 */
@Data
public class UserInfoPO {


    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String sendId;
    /**
     *
     */
    private String sendMail;
    /**
     *
     */
    private String sendPassword;
    /**
     *
     */
    private String nickName;

    /**
     * 接收人邮箱
     */
    private String recEmail;
    /**
     * 接收人电话
     */
    private String recPhone;
    /**
     * 城市id
     */
    private String cityCode;
    /*
     * 城市名称
     */
    private String cityName;

}
