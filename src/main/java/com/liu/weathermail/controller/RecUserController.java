package com.liu.weathermail.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liu.weathermail.entity.RecUserEntity;
import com.liu.weathermail.service.RecUserService;

import javax.annotation.Resource;

/**
 * 
 *
 * @author liushuaibiao
 * @email misteryliu@outlook.com
 * @date 2023-11-18 16:46:18
 */
@RestController
@RequestMapping("WeatherPush/recuser")
public class RecUserController {
//    @Resource
//    private RecUserService recUserService;

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = recUserService.queryPage(params);
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
//		RecUserEntity recUser = recUserService.getById(id);
//
//        return R.ok().put("recUser", recUser);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody RecUserEntity recUser){
//		recUserService.save(recUser);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody RecUserEntity recUser){
//		recUserService.updateById(recUser);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//		recUserService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
