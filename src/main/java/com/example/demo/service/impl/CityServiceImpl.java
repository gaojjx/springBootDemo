package com.example.demo.service.impl;

import com.example.demo.domain.City;
import com.example.demo.mapper.CityMapper;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    @Autowired
    private CityMapper cityMapper;

    @Override
    public List<City> getAllCities() {
        return cityMapper.getAllCity();
    }

    @Override
    public City findCityById(Integer id) {
        return cityMapper.findCityById(id);
    }

    @Override
    public String updateCity(City city) {
        int result = cityMapper.updateCity(city);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String deleteCity(Integer id) {
        int result = cityMapper.deleteCity(id);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String addCity(City city) {
        int result = cityMapper.addCity(city);
        return result == 0 ? "fail" : "success";
    }
}
