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
@TableName("daily")
public class DailyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Date fxDate;
	/**
	 * 
	 */
	private String tempMax;
	/**
	 * 
	 */
	private String tempMin;
	/**
	 * 
	 */
	private String textDay;
	/**
	 * 
	 */
	private String winddirday;
	/**
	 * 
	 */
	private String windscaleday;
	/**
	 * 
	 */
	private Date addtime;
	/**
	 * 
	 */
	private Date updatetime;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 
	 */
	private Integer unknown;
	/**
	 * 
	 */
	private String cityCode;

}
