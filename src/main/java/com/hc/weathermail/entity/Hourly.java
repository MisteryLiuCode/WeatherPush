package com.hc.weathermail.entity;

import java.util.Date;

public class Hourly {
    private Date fxTime;
    private String temp;
    private String icon;
    private String text;
    private String wind360;
    private String windDir;
    private String windScale;
    private String windSpeed;
    private String humidity;
    private String pop;
    private String precip;
    private String pressure;
    private String cloud;
    private String dew;
    public void setFxTime(Date fxTime) {
        this.fxTime = fxTime;
    }
    public Date getFxTime() {
        return fxTime;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }
    public String getTemp() {
        return temp;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return icon;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setWind360(String wind360) {
        this.wind360 = wind360;
    }
    public String getWind360() {
        return wind360;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir;
    }
    public String getWindDir() {
        return windDir;
    }

    public String getWindScale() {
        return windScale;
    }

    public void setWindScale(String windScale) {
        this.windScale = windScale;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }
    public String getWindSpeed() {
        return windSpeed;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }
    public String getHumidity() {
        return humidity;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }
    public String getPop() {
        return pop;
    }

    public void setPrecip(String precip) {
        this.precip = precip;
    }
    public String getPrecip() {
        return precip;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }
    public String getPressure() {
        return pressure;
    }

    public void setCloud(String cloud) {
        this.cloud = cloud;
    }
    public String getCloud() {
        return cloud;
    }

    public void setDew(String dew) {
        this.dew = dew;
    }
    public String getDew() {
        return dew;
    }
}
