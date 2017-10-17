package com.example.demo.controller;

import com.example.demo.entity.Regionalism;
import com.example.demo.service.RegionalismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionalismController {
    @Autowired
    private RegionalismService regionalismService;

    @RequestMapping("addRegion")

    public String addRegion() throws IOException {
        return regionalismService.addRegions();
    }

    @GetMapping("getAll")
    public List<Regionalism> getAll() {
        return regionalismService.getAll();
    }
}
