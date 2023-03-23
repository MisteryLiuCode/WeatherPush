package com.hc.weathermail.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@ToString
public class WarningVO {

    private String code;
    private Date updateTime;
    private String fxLink;
    private List<Warning> warning;
    private Refer refer;
}
