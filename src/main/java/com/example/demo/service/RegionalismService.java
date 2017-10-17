package com.example.demo.service;

import com.example.demo.entity.Regionalism;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RegionalismService {
    List<Regionalism> findList(Regionalism regionalism);

    List<Regionalism> getAll();

    String addRegions();
}
