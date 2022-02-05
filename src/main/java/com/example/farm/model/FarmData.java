package com.example.farm.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "farm_data")
public class FarmData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "farm")
    private String farm;

    @Column(name = "date")
    private Date date;

    @Column(name = "metric_type")
    private String metricType;

    @Column(name = "metric_value")
    private Double metricValue;
}
