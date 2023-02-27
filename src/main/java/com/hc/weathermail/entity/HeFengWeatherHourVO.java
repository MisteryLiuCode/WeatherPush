package com.hc.weathermail.entity;

import java.util.Date;
import java.util.List;

public class HeFengWeatherHourVO {
    private String code;
    private Date updateTime;
    private String fxLink;
    private List<Hourly> hourly;
    private Refer refer;
    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setFxLink(String fxLink) {
        this.fxLink = fxLink;
    }
    public String getFxLink() {
        return fxLink;
    }

    public void setHourly(List<Hourly> hourly) {
        this.hourly = hourly;
    }
    public List<Hourly> getHourly() {
        return hourly;
    }

    public void setRefer(Refer refer) {
        this.refer = refer;
    }
    public Refer getRefer() {
        return refer;
    }
}
