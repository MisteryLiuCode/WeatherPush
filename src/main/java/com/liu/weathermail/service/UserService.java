package com.liu.weathermail.service;

import com.liu.weathermail.entity.req.SaveRecUserInfoReq;
import com.liu.weathermail.entity.req.SaveSendUserInfoReq;

/**
 * 
 *
 * @author liushuaibiao
 * @email misteryliu@outlook.com
 * @date 2023-11-18 16:46:18
 */
public interface UserService {

    Boolean saveSendUser(SaveSendUserInfoReq req);
    Boolean saveRecUser(SaveRecUserInfoReq req);





}

