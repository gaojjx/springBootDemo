package com.example.demo.mapper;

import com.example.demo.entity.Regionalism;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegionalismMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table regionalism
     *
     * @mbg.generated Mon Sep 25 11:06:52 CST 2017
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table regionalism
     *
     * @mbg.generated Mon Sep 25 11:06:52 CST 2017
     */
    int insert(Regionalism record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table regionalism
     *
     * @mbg.generated Mon Sep 25 11:06:52 CST 2017
     */
    Regionalism selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table regionalism
     *
     * @mbg.generated Mon Sep 25 11:06:52 CST 2017
     */
    List<Regionalism> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table regionalism
     *
     * @mbg.generated Mon Sep 25 11:06:52 CST 2017
     */
    int updateByPrimaryKey(Regionalism record);

    int insertBatch(List<Regionalism> list);
}