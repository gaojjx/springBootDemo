package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updateUser(User user) {
        int result = userMapper.updateByPrimaryKey(user);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String deleteUserById(Integer id) {
        int result = userMapper.deleteByPrimaryKey(id);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String addUser(User user) {
        int result = userMapper.insert(user);
        return result == 0 ? "fail" : "success";
    }
}
