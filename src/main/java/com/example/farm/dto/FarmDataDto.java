package com.example.farm.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FarmDataDto {
    private Long id;
    private String farm;
    private Date date;
    private String metricType;
    private Double metricValue;
}
