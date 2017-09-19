package com.example.demo.controller;

import com.example.demo.entity.City;
import com.example.demo.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateCity(@RequestBody City city) {
        return cityService.updateCity(city);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addCity(@RequestBody City city) {
        return cityService.addCity(city);
    }

}
