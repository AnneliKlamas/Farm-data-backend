package com.example.farm.controller;

import com.example.farm.dto.FarmDataDto;
import com.example.farm.repository.FarmDataRepository;
import com.example.farm.service.FarmDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/farm-data")
public class FarmDataController {

    @Autowired
    private FarmDataService farmDataService;
    @Autowired
    private FarmDataRepository farmDataRepository;

    @GetMapping("")
    private List<FarmDataDto> getFarmData() {
        return farmDataService.getAll();
    }

    @PostMapping("/upload")
    public String uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        return farmDataService.upload(file);
    }

    @DeleteMapping("/delete")
    @Transactional
    void deleteAllData(){farmDataRepository.deleteAll();}
}
