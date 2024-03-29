package com.liu.weathermail.entity.req;

import lombok.Data;

import java.util.Date;

/**
 * All rights Reserved, Designed By misteryliu. 保存用户信息
 *
 * @author misteryliu@outlook.com
 * @since 2023/11/18 15:54 Copyright ©2023 misteryliu. All rights reserved.
 */

@Data
public class SaveRecUserInfoReq {

    /**
     * 发送人id
     */
    private String sendId;

    /*
    接收人昵称
     */
    private String recName;

    /*
    接收人邮箱
     */
    private String recEmail;

    /*
    接收人手机号码
     */
    private String recPhone;

    /*
    接收人收到消息时间
     */
    private Date recTime;

    /*
    城市码
     */
    private String cityCode;

    /*
    城市名称
     */
    private String cityName;

    /*
    是否开启 1:开启 0:关闭
    */
    private Integer status;

    /**
     * 编辑或者新增
     * @see com.liu.weathermail.enums.InsertOrUpdateEnum
     */
    private String insertOrUpdate;

}
