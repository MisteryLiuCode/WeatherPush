package com.liu.weathermail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weathermail.entity.PersonInfoEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PersonInfoDao extends BaseMapper<PersonInfoEntity> {
}
