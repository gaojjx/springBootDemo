package com.example.demo.service.impl;

import com.example.demo.entity.City;
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
        return cityMapper.selectAll();
    }

    @Override
    public City findCityById(Integer id) {
        return cityMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updateCity(City city) {
        int result = cityMapper.updateByPrimaryKey(city);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String deleteCity(Integer id) {
        int result = cityMapper.deleteByPrimaryKey(id);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String addCity(City city) {
        int result = cityMapper.insert(city);
        return result == 0 ? "fail" : "success";
    }
}
