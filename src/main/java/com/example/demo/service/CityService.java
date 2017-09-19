package com.example.demo.service;

import com.example.demo.entity.City;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CityService {
    List<City> getAllCities();

    City findCityById(Integer id);

    String updateCity(City city);

    String deleteCity(Integer id);

    String addCity(City city);
}
