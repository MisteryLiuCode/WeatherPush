package com.liu.weathermail.entity;

import java.util.List;

public class Refer {
    private List<String> sources;
    private List<String> license;
    public void setSources(List<String> sources) {
        this.sources = sources;
    }
    public List<String> getSources() {
        return sources;
    }

    public void setLicense(List<String> license) {
        this.license = license;
    }
    public List<String> getLicense() {
        return license;
    }
}
