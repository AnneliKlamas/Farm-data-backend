package com.example.farm.service;

import com.example.farm.dto.FarmDataDto;
import com.example.farm.model.FarmData;
import com.example.farm.repository.FarmDataRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FarmDataService {
    @Autowired
    private FarmDataRepository farmDataRepository;

    public List<FarmDataDto> getAll() {
        List<FarmData> data = farmDataRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(data, new TypeToken<List<FarmDataDto>>() {
        }.getType());
    }
}
