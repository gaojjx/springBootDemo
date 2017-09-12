package com.example.demo.controller;

import com.example.demo.domain.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("")
    public List<City> getAllCities() {
        return cityService.getAllCities();
    }

    @RequestMapping("/{id}")
    public City findCityById(@PathVariable(value = "id") Integer id) {
        return cityService.findCityById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String deleteCity(@PathVariable Integer id) {
        return cityService.deleteCity(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String updateCity(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public String addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

}
