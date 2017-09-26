package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Regionalism;
import com.example.demo.mapper.RegionalismMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class RegionalismController {
    @Autowired
    private RegionalismMapper regionalismMapper;

    @RequestMapping("addRegion")
    public String addRegion() throws IOException {
        File path = new File("/Users/gaojjx/code/springBootDemo/src/main/resources/town");
        File[] files = path.listFiles();
        Regionalism regionalism;
        List<Regionalism> regionalismList = new ArrayList<>();
        String parentCode;
        byte[] bytes;
        JSONObject jsonObject;
        for (File file : files) {
            parentCode = file.getName().substring(0, 6);
            bytes = Files.readAllBytes(Paths.get(file.getPath()));
            jsonObject = (JSONObject) JSONObject.parse(bytes);
            Set<String> set = jsonObject.keySet();
            for (String key : set) {
                regionalism = new Regionalism();
                regionalism.setParentCode(Integer.valueOf(parentCode));
                regionalism.setRegionCode(Long.valueOf(key));
                //获取文件名作为上级编号
                regionalism.setRegionName(jsonObject.get(key).toString());
                System.out.println(regionalism);
                regionalismList.add(regionalism);
            }
        }
        regionalismMapper.insertBatch(regionalismList);
        return "success, 插入" + regionalismList.size() + "条!";
    }
}
