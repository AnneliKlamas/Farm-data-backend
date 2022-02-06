package com.example.farm.service;

import com.example.farm.dto.FarmDataDto;
import com.example.farm.enums.MetricType;
import com.example.farm.model.FarmData;
import com.example.farm.repository.FarmDataRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.farm.enums.MetricType.*;

@Service
public class FarmDataService {

    @Autowired
    private FarmDataRepository repository;

    public List<FarmDataDto> getAll() {
        List<FarmData> data = repository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(data, new TypeToken<List<FarmDataDto>>() {
        }.getType());
    }

    public String upload(MultipartFile file) throws IOException {
        List<FarmData> farmDataList = new ArrayList<>();
        InputStream inputStream = file.getInputStream();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setMaxColumns(60000);
        CsvParser parser = new CsvParser(settings);
        try {
            List<Record> parseAllRecords = parser.parseAllRecords(inputStream);
            parseAllRecords.forEach(record -> {
                Double value;
                Date date;
                String metricType = record.getString("sensorType");
                try {
                    value = Double.parseDouble(record.getString("value"));
                    date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(record.getString("datetime"));
                    if (validateMetric(metricType, value) & date != null) {
                        FarmData data = new FarmData();
                        data.setFarm(record.getString("location"));
                        data.setDate(date);
                        data.setMetricType(getMetric(metricType));
                        data.setMetricValue(value);
                        farmDataList.add(data);
                    }
                } catch (Exception ignored) {
                }
            });
            repository.saveAll(farmDataList);
            return String.valueOf(farmDataList.size());
        } catch (ArrayIndexOutOfBoundsException e) {
            return "Too many rows in a file";
        }
    }

    private Boolean validateMetric(String metric, Double value) {
        if (value == null || metric == null || metric.isEmpty()) {
            return false;
        }
        if (PH.label.equals(metric)) {
            return value >= 0 & value <= 14;
        }
        if (TEMPERATURE.label.equals(metric)) {
            return value >= -50 & value <= 100;
        }
        if (RAINFALL.label.equals(metric)) {
            return value >= 0 & value <= 500;
        }
        return false;
    }

    private MetricType getMetric(String label) {
        if (RAINFALL.label.equals(label)) return RAINFALL;
        if (TEMPERATURE.label.equals(label)) return TEMPERATURE;
        if (PH.label.equals(label)) return PH;
        else throw new NoSuchElementException();
    }


}
