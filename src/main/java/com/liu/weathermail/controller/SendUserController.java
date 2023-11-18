package com.liu.weathermail.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liu.weathermail.entity.SendUserEntity;
import com.liu.weathermail.service.UserService;

import javax.annotation.Resource;

/**
 * 
 *
 * @author liushuaibiao
 * @email misteryliu@outlook.com
 * @date 2023-11-18 16:46:18
 */
@RestController
@RequestMapping("WeatherPush/senduser")
public class SendUserController {
//    @Resource
//    private UserService userService;

    /**
     * 列表
     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = userService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    public R info(@PathVariable("id") Integer id){
//		SendUserEntity sendUser = userService.getById(id);
//
//        return R.ok().put("sendUser", sendUser);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody SendUserEntity sendUser){
//		userService.save(sendUser);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody SendUserEntity sendUser){
//		userService.updateById(sendUser);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//		userService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
