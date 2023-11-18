package com.liu.weathermail.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liu.weathermail.entity.DailyEntity;
import com.liu.weathermail.service.DailyService;

import javax.annotation.Resource;

/**
 * 
 *
 * @author liushuaibiao
 * @email misteryliu@outlook.com
 * @date 2023-11-18 16:46:18
 */
@RestController
@RequestMapping("WeatherPush/daily")
public class DailyController {
//    @Resource
//    private DailyService dailyService;

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = dailyService.queryPage(params);
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
//		DailyEntity daily = dailyService.getById(id);
//
//        return R.ok().put("daily", daily);
//    }
//
//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody DailyEntity daily){
//		dailyService.save(daily);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody DailyEntity daily){
//		dailyService.updateById(daily);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//		dailyService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

}
