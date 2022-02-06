package com.example.farm.dto;

import com.example.farm.enums.MetricType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FarmDataDto {
    private Long id;
    private String farm;
    private Date date;
    private MetricType metricType;
    private Double metricValue;
}
