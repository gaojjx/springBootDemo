package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

    @Override
    public HSSFWorkbook exportUsers() {
        InputStream inputStream = this.getClass().getResourceAsStream("/template/excel/user.xls");
        HSSFWorkbook workbook = null;
        try {
            POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
            workbook = new HSSFWorkbook(poifsFileSystem);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        HSSFSheet sheet = workbook.createSheet("用户");
//        HSSFRow title = sheet.createRow(0);
//        HSSFCell titleCell = title.createCell(0);
//        titleCell.setCellValue("ID");
//        titleCell = title.createCell(1);
//        titleCell.setCellValue("年龄");
//        titleCell = title.createCell(2);
//        titleCell.setCellValue("名字");
//        titleCell = title.createCell(3);
//        titleCell.setCellValue("描述");
        HSSFSheet sheet = workbook.getSheetAt(0);
        List<User> list = userMapper.selectAll();
        if (list != null && list.size() > 0) {
            HSSFRow row;
            HSSFCell cell;
            int i = 1;
            for (User user : list) {
                row = sheet.createRow(i);
                cell = row.createCell(0);
                cell.setCellValue(user.getId());
                cell = row.createCell(1);
                cell.setCellValue(user.getAge());
                cell = row.createCell(2);
                cell.setCellValue(user.getName());
                cell = row.createCell(3);
                cell.setCellValue(user.getDescription());
            }
        }
        return workbook;
    }
}
