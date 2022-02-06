package com.example.farm.enums;

import java.util.NoSuchElementException;

public enum MetricType {
    RAINFALL("rainFall"),
    TEMPERATURE("temperature"),
    PH("pH");

    public final String label;

    private MetricType(String label) {
        this.label = label;
    }
}
