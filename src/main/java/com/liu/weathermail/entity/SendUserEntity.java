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
@TableName("send_user")
public class SendUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private String id;
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
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;

}
