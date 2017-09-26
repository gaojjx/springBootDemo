package com.example.demo.service.impl;

import com.example.demo.mapper.RegionMapper;
import com.example.demo.service.RegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RegionServiceImplTest {
    @Autowired
    private RegionService regionService;

    @Test
    public void start() throws Exception {
        regionService.start();
    }

}