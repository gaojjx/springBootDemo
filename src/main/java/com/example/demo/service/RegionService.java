package com.example.demo.service;

import com.example.demo.entity.Region;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegionService {
    List<Region> findRegion(Region region);

    Region start();

}
