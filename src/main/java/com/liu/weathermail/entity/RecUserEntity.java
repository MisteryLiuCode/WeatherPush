package com.liu.weathermail.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author liushuaibiao
 * @email misteryliu@outlook.com
 * @date 2023-11-18 16:46:18
 */
@Data
@TableName("rec_user")
public class RecUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private String id;
	/**
	 * 发送人id
	 */
	private String sendId;
	/**
	 * 接收人邮箱
	 */
	private String recEmail;
	/**
	 * 接收人电话
	 */
	private String recPhone;
	/**
	 * 接收人姓名
	 */
	private String recName;
	/**
	 * 城市id
	 */
	private String cityCode;
	/*
	 * 城市名称
	 */
	private String cityName;
	/**
	 * 接收时间
	 */
	private Date recTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/*
	 * 数据状态
	 */
	private String status;

}
