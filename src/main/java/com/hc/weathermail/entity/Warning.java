package com.hc.weathermail.entity;

import lombok.Data;

@Data
public class Warning {
    private String id;

    private String sender;

    private String pubTime;

    private String title;

    private String startTime;

    private String endTime;

    private String status;

    private String level;

    private String type;

    private String typeName;

    private String text;

    private String related;
}
