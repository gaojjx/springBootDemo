package com.example.demo.mapper;

import com.example.demo.domain.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityMapper {

    @Select("select id, name, country from test.city")
    List<City> getAllCity();

    @Select("select id, name, country from test.city where id = #{id}")
    City findCityById(@Param("id") Integer id);

    @Insert("insert into test.city(name, country) VALUES (#{name}, #{country})")
    int addCity(City city);

    @Delete("DELETE from test.city WHERE id = #{id}")
    int deleteCity(Integer id);

    @Update("UPDATE test.city set name = #{name}, country = #{country} where id = #{id}")
    int updateCity(City city);

}
