package com.example.demo.mapper;

import com.example.demo.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * from test.user")
    List<User> getUserList();

    @Select("SELECT * from test.user where id = #{id}")
    User findUserById(Integer id);

    @Update("UPDATE test.user set name = #{name}, age = #{age}, description = #{description} where id = #{id}")
    int updateUser(User user);

    @Insert("INSERT INTO test.user(age, name, description) values (#{age}, #{name}, #{description})")
    int addUser(User user);

    @Delete("delete from test.user where id = #{id}")
    int deleteUserById(Integer id);
}
