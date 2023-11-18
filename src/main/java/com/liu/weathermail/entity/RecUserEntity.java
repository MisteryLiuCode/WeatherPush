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
	 * 
	 */
	@TableId
	private String id;
	/**
	 * 
	 */
	private String sendId;
	/**
	 * 
	 */
	private String recEmail;
	/**
	 * 
	 */
	private String recPhone;
	/**
	 * 
	 */
	private String cityCode;
	/**
	 * 
	 */
	private String cityName;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
