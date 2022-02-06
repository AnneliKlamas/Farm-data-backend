package com.example.farm.controller;

import com.example.farm.dto.FarmDataDto;
import com.example.farm.service.FarmDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/farm-data")
public class FarmDataController {

    @Autowired
    private FarmDataService farmDataService;

    @GetMapping("")
    private List<FarmDataDto> getFarmData() {
        return farmDataService.getAll();
    }
}
