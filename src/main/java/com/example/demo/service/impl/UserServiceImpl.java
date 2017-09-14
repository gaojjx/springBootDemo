package com.example.demo.service.impl;

import com.example.demo.domain.User;
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
        return userMapper.getUserList();
    }

    @Override
    public User findUserById(Integer id) {
        return userMapper.findUserById(id);
    }

    @Override
    public String updateUser(User user) {
        int result = userMapper.updateUser(user);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String deleteUserById(Integer id) {
        int result = userMapper.deleteUserById(id);
        return result == 0 ? "fail" : "success";
    }

    @Override
    public String addUser(User user) {
        int result = userMapper.addUser(user);
        return result == 0 ? "fail" : "success";
    }
}
