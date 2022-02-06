package com.example.farm.controller;

import com.example.farm.dto.FarmDataDto;
import com.example.farm.service.FarmDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        return farmDataService.upload(file);
    }
}
