package com.liu.weathermail.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liu.weathermail.entity.PersonInfoEntity;
import com.liu.weathermail.entity.SysDictEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDictDao extends BaseMapper<SysDictEntity> {
}
