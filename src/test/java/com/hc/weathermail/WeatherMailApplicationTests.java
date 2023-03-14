package com.hc.weathermail;

import com.hc.weathermail.entity.*;
import com.hc.weathermail.utils.ConfigUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.configuration.Configuration;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.client.RestTemplate;
import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
class WeatherMailApplicationTests {

    @Resource
    private JdbcTemplate jdbcTemplate;

    /**
     * 测试获取天气信息
     */
    @Test
    public void testGetWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            // 添加拦截器，使用 gzip 编码提交
            ClientHttpRequestInterceptor interceptor = (httpRequest, bytes, execution) -> {
                httpRequest.getHeaders().set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
                httpRequest.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
                httpRequest.getHeaders().set(HttpHeaders.ACCEPT_ENCODING, "gzip");   // 使用 gzip 编码提交
                return execution.execute(httpRequest, bytes);
            };
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
            RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
            restTemplate.getInterceptors().add(interceptor);

            String url = weatherConfig.getString("DayUrl");
            String key = weatherConfig.getString("key");
            String cityid = weatherConfig.getString("FuZhouCityId");
            // 使用restTemplate发送请求
//        RestTemplate restTemplate = new RestTemplate();
            // 准备参数
            String resUrl = url + "?" + "location=" + cityid +
                    "&" + "key=" + key;
            ResponseEntity<HeFengWeatherDayVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherDayVO.class);
            System.out.println(res.getBody().getNow());
        }

    }


    /**
     * 测试获取当天天气信息
     */
    @Test
    public void testHeFengWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String resUrl = getDayResUrl(weatherConfig);

            ResponseEntity<HeFengWeatherDayVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherDayVO.class);
            HeFengWeatherDayVO weatherVo = res.getBody();
            System.out.println(weatherVo);
        }
    }

    /**
     * 测试逐小时获取当天天气信息
     */
    @Test
    public void testHeFengHourWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String resUrl = getHourResUrl(weatherConfig);
            ResponseEntity<HeFengWeatherHourVO> res = restTemplate.getForEntity(resUrl, HeFengWeatherHourVO.class);
            List<Hourly> hourlyList = res.getBody().getHourly();
            Hourly resHourly = new Hourly();
//            获取当前时间
            SimpleDateFormat nowSdf = new SimpleDateFormat("dd");// 格式化时间
            String nowDay = nowSdf.format(new Date());
            //获取8点到11点的天气数据
            if (hourlyList != null) {
                List<Hourly> newHourList = hourlyList.subList(0, 15);
                for (Hourly hourly : newHourList) {
                    String rainDay = nowSdf.format(hourly.getFxTime());
                    String rain = rainDay.equals(nowDay) ? "今天" : "明天";
                    log.info("比较时间结果" + rain);
                    if (hourly.getText().equals(WeatherEnum.B.getCode())) {
                        resHourly = hourly;
                        System.out.println("下雨天气：" + hourly);
                        break;
                    }
                }
            }
            System.out.println(resHourly);
        }
    }

    //    测试连接数据库
    @Test
    public void testDbWeather() {
        RowMapper<Daily> rowMapper = new BeanPropertyRowMapper<>(Daily.class);
        List<Daily> dailies = jdbcTemplate.query("select * from t_today_weather", rowMapper);
        log.info("获取的天气:" + dailies.toString());
    }

    //   测试插入今日天气
    @Test
    public void testSaveToDayWeather() {
        Configuration weatherConfig = ConfigUtil.getHeFengWeatherConfig();
        if (weatherConfig != null) {
            RestTemplate restTemplate = getTemplate();
            String tomorrowResUrl = ConfigUtil.getTomorrowResUrl(weatherConfig);
            ResponseEntity<TomorrowWeatherVO> tomorrowRes = restTemplate.getForEntity(tomorrowResUrl, TomorrowWeatherVO.class);
            List<Daily> dailyList = tomorrowRes.getBody().getDaily();
            if (dailyList!=null){
                Daily toDay = dailyList.get(0);
                //                获取昨日天气
                RowMapper<Daily> rowMapper = new BeanPropertyRowMapper<>(Daily.class);
                List<Daily> dailies = jdbcTemplate.query("select * from t_today_weather order by add_time desc",rowMapper);
                if (dailies!=null) {
                    Daily yesterday = dailies.get(0);
//                    判断两天的温差
                    Integer difTemp = Integer.parseInt(yesterday.getTempMax()) - Integer.parseInt(toDay.getTempMax());
                    log.info("两天温差为{}度",difTemp);
                }
                //                今天的天气入库
                String sql="INSERT INTO t_today_weather VALUES(?,?,?,?,?,?,?,?,?,?,?)";
                //影响的行数
                int update = jdbcTemplate.update(sql,null,toDay.getFxDate(),toDay.getTempMax(),
                        toDay.getTempMin(),toDay.getTextDay(),toDay.getWindDirDay(),toDay.getWindScaleDay(),
                        new Date(),new Date(),"定时插入今日天气",1);
                log.info("插入成功{}条天气数据",update);
            }
        }
    }


    private RestTemplate getTemplate() {
        // 添加拦截器，使用 gzip 编码提交
        ClientHttpRequestInterceptor interceptor = (httpRequest, bytes, execution) -> {
            httpRequest.getHeaders().set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.97 Safari/537.36");
            httpRequest.getHeaders().set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
            httpRequest.getHeaders().set(HttpHeaders.ACCEPT_ENCODING, "gzip");   // 使用 gzip 编码提交
            return execution.execute(httpRequest, bytes);
        };
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build());
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        restTemplate.getInterceptors().add(interceptor);
        return restTemplate;
    }

    /**
     * description: 逐小时天气url
     *
     * @param weatherConfig
     * @return java.lang.String
     */
    private String getHourResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("HourUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("BeiJingCityid");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

    /**
     * description: 每日天气url
     *
     * @param weatherConfig
     * @return java.lang.String
     */
    private String getDayResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("DayUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

    public static String getTomorrowResUrl(Configuration weatherConfig) {
        String url = weatherConfig.getString("TomorrowUrl");
        String key = weatherConfig.getString("key");
        String cityid = weatherConfig.getString("FuZhouCityId");
        // 准备参数
        String resUrl = url + "?" + "location=" + cityid +
                "&" + "key=" + key;
        return resUrl;
    }

}
