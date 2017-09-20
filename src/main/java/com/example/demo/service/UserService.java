package com.example.demo.service;

import com.example.demo.entity.User;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();

    User findUserById(Integer id);

    String updateUser(User user);

    String deleteUserById(Integer id);

    String addUser(User user);

    HSSFWorkbook exportUsers();

}
