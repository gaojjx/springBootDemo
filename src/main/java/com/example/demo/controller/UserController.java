package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("{id}")
    public User findUserById(@PathVariable(value = "id") Integer id) {
        return userService.findUserById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable(value = "id") Integer id) {
        return userService.deleteUserById(id);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateUser(@RequestBody User user) {
        return userService.updateUser(user);
    }

}
