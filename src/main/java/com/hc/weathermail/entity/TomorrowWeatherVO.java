package com.hc.weathermail.entity;

import java.util.Date;
import java.util.List;

public class TomorrowWeatherVO {
    private String code;
    private Date updateTime;
    private String fxLink;
    private List<Daily> daily;
    private Refer refer;

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return this.code;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return this.updateTime;
    }
    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }
    public String getFxLink() {
        return this.fxLink;
    }
    public void setDaily(List<Daily> daily) {
        this.daily = daily;
    }
    public List<Daily> getDaily() {
        return this.daily;
    }
    public void setRefer(Refer refer) {
        this.refer = refer;
    }
    public Refer getRefer() {
        return this.refer;
    }
}
