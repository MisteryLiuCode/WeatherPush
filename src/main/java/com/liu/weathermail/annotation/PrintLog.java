package com.liu.weathermail.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: houcheng
 * @date: 2021/7/24 11:15
 * @version: V1.0
 * @description: 用作方法上，打印方法执行信息
 * @modify:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PrintLog {
}
