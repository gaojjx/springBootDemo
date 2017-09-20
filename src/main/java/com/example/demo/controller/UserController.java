package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

    @RequestMapping(value = "exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            String fileName = "用户导出";
            response.setContentType("application/binary;charset=ISO8859_1");
            String agent = request.getHeader("USER-AGENT").toLowerCase();
            String codedFileName = java.net.URLEncoder.encode(fileName, "UTF-8");
            if (agent.contains("firefox")) {
                response.setCharacterEncoding("utf-8");
                response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes(), "ISO-8859-1") + ".xls");
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + codedFileName + ".xls");
            }
            HSSFWorkbook workbook = userService.exportUsers();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
