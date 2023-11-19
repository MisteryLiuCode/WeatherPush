package com.liu.weathermail.service.impl;

import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liu.weathermail.dao.DailyDao;
import com.liu.weathermail.dao.RecUserDao;
import com.liu.weathermail.entity.*;
import com.liu.weathermail.entity.req.WeatherReq;
import com.liu.weathermail.enums.StatusEnum;
import com.liu.weathermail.service.WeatherService;
import com.liu.weathermail.utils.ConfigUtil;
import com.liu.weathermail.utils.SendSmsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * All rights Reserved, Designed By misteryliu.
 * 天气查询
 *
 * @author misteryliu@outlook.com
 * @since 2023/11/19 14:51 Copyright ©2023 misteryliu. All rights reserved.
 */
@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    @Resource
    private DailyDao dailyDao;

    @Resource
    private RecUserDao recUserDao;


    @Override
    public Boolean getWeather(WeatherReq req) {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            log.info("开始执行定时任务,查询当前时间到之后的14个小时内是否下雨");
            RestTemplate restTemplate = ConfigUtil.getTemplate();
            String resUrl = ConfigUtil.getHourResUrl(weatherConfig);
            String recEmail = req.getRecEmail();

            ResponseEntity<HeFengWeatherHourVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherHourVO.class);
            List<Hourly> hourlyList = res.getBody().getHourly();
            if (hourlyList != null) {
                List<Hourly> newHourList = hourlyList.subList(0, 15);
                for (Hourly hourly : newHourList) {
                    if (hourly.getText().contains(WeatherEnum.B.getCode())) {
                        // 发送邮件
                        StringBuilder sb = new StringBuilder();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
                        sb.append("预计").append(simpleDateFormat.format(hourly.getFxTime())).append("点开始下雨，")
                                .append("出门之前收衣服，记得带伞");
                        log.info("邮件内容：" + sb.toString());
                        MailUtil.send(recEmail, "天气情况", sb.toString(), false);
                        //                发送短信
                        String toDayWeatherId = weatherConfig.getString("goWorkRainId");
                        String recPhone = req.getRecPhone();
                        String[] phoneAddressee = new String[] { recPhone };
                        String[] args = { simpleDateFormat.format(hourly.getFxTime()) };
                        SendSmsUtil.sendSms(toDayWeatherId, phoneAddressee, args);
                        break;
                    }
                }
            }
            /*
             查询天气温度是否波动很大
             1.查询今天天气,与昨天天气比较,如果温差大于10度,则发送消息
             2.把今天的天气入库,作为明天比较对象
             */
            String tomorrowResUrl = ConfigUtil.getTomorrowResUrl(weatherConfig);
            ResponseEntity<TomorrowWeatherVO> tomorrowRes = restTemplate.getForEntity(tomorrowResUrl,
                    TomorrowWeatherVO.class);
            List<Daily> dailyList = tomorrowRes.getBody().getDaily();
            if (dailyList != null) {
                //                今日天气
                Daily toDay = dailyList.get(0);

                QueryWrapper<DailyEntity> wrapper = new QueryWrapper<>();
                wrapper.eq("city_code",req.getCityCode())
                        .orderByDesc("create_time")
                        .eq("rec_id",req.getRecId());
                List<DailyEntity> dailyEntities = dailyDao.selectList(wrapper);
                DailyEntity yesterday = dailyEntities.get(0);
                if (!CollectionUtils.isEmpty(dailyEntities)) {
                    //                    判断两天的温差
                    Integer difTemp = Integer.parseInt(toDay.getTempMax()) - Integer.parseInt(yesterday.getTempMax());
                    log.info("今天和昨天温差为{}度", difTemp);
                    if (Math.abs(difTemp) > 9) {
                        String msg = difTemp > 0
                                ? "今天和昨天温差为" + difTemp + "度，升温了,可以穿凉快点"
                                : "今天和昨天温差为" + difTemp + "度，降温了,注意保暖";
                        log.info("邮件内容：" + msg);
                        MailUtil.send(recEmail, "天气温差变化", msg, false);
                        //                        发送短信
                        String liuAddressee = weatherConfig.getString("zprAddressee");
                        String[] addressee = new String[] { liuAddressee };
                        String[] args = { toDay.getTempMax() };
                        String templateId = difTemp > 0
                                ? weatherConfig.getString("heatUpId")
                                : weatherConfig.getString("HeatDownId");
                        SendSmsUtil.sendSms(templateId, addressee, args);
                    }
                }
                //今日天气入库
                saveTodayWeather(req.getRecId(),req.getCityCode());
            }
        }
        return true;
    }

    /**
     * 新用户入库
     *
     * @param recId
 * @param cityId
     * @return void
     * @since 2023/11/19 18:48 by misteryliu
     **/
    public void saveTodayWeather(String recId,String cityId) {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        RestTemplate restTemplate = ConfigUtil.getTemplate();
        String tomorrowResUrl = ConfigUtil.getTomorrowResUrl(weatherConfig);
        ResponseEntity<TomorrowWeatherVO> tomorrowRes = restTemplate.getForEntity(tomorrowResUrl,
                TomorrowWeatherVO.class);
        List<Daily> dailyList = tomorrowRes.getBody().getDaily();
        if (dailyList != null) {
            //                今日天气
            Daily toDay = dailyList.get(0);
            //                今日天气入库
            DailyEntity dailyEntity = new DailyEntity();
            dailyEntity.setRecId(recId);
            dailyEntity.setFxDate(toDay.getFxDate());
            dailyEntity.setCityCode(cityId);
            dailyEntity.setTempMax(toDay.getTempMax());
            dailyEntity.setTempMin(toDay.getTempMin());
            dailyEntity.setTextDay(toDay.getTextDay());
            dailyEntity.setWindDirDay(toDay.getWindDirDay());
            dailyEntity.setWindScaleDay(toDay.getWindScaleDay());
            dailyEntity.setStatus(StatusEnum.Y.getCode());
            dailyEntity.setCreateTime(new Date());
            dailyEntity.setUpdateTime(new Date());
            dailyEntity.setRemark("定时插入今日天气");
            int insert = dailyDao.insert(dailyEntity);
            log.info("成功插入{}条当日天气数据", insert);
        }
    }
}
