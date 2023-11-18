package com.liu.weathermail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weathermail.entity.SendUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * 
 * @author liushuaibiao
 * @email misteryliu@outlook.com
 * @date 2023-11-18 16:46:18
 */
@Mapper
public interface SendUserDao extends BaseMapper<SendUserEntity> {

    int insertUser(SendUserEntity sendUser);
	
}
