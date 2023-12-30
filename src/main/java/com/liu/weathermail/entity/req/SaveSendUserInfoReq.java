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
public class SaveSendUserInfoReq {

    /*
    发送人邮箱
     */
    private String sendMail;

    /*
    发送人密码
     */
    private String sendPassword;

    /*
    是否开启 1:开启 0:关闭
    */
    private Integer status;

}
