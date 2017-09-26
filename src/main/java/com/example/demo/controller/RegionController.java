package com.example.demo.controller;

import com.example.demo.entity.Region;
import com.example.demo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @RequestMapping("/parseJson")
    public Region start() {
        return regionService.start();
    }
}
