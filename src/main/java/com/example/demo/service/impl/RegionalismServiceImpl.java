package com.example.demo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Regionalism;
import com.example.demo.mapper.RegionalismMapper;
import com.example.demo.service.RegionalismService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class RegionalismServiceImpl implements RegionalismService {
    @Autowired
    private RegionalismMapper regionalismMapper;

    public static List<Regionalism> list = new ArrayList<>();

    @Override
    public List<Regionalism> findList(Regionalism regionalism) {
        return regionalismMapper.findList(regionalism);
    }

    @Override
    public List<Regionalism> getAll() {
        if (list.size() > 0) {
            return list;
        }
        Regionalism root = new Regionalism();
        root.setParentCode(0);
        List<Regionalism> provinces = findList(root);
        for (Regionalism province : provinces) {
            Regionalism cityParam = getParam(province);
            List<Regionalism> cities = findList(cityParam);
            for (Regionalism city : cities) {
                Regionalism regionParam = getParam(city);
                List<Regionalism> regions = findList(regionParam);
//                for (Regionalism region : regions) {
//                    Regionalism streetParam = getParam(region);
//                    List<Regionalism> streets = findList(streetParam);
//                    region.setSubList(streets);
//                }
                city.setSubList(regions);
            }
            province.setSubList(cities);
            list.add(province);
        }
        return list;
    }

    private Regionalism getParam(Regionalism parent) {
        Regionalism cityParam = new Regionalism();
        cityParam.setParentCode(parent.getRegionCode().intValue());
        return cityParam;
    }

    @Override
    public String addRegions() {
        File path = new File("/Users/gaojjx/code/springBootDemo/src/main/resources/town");
        File[] files = path.listFiles();
        Regionalism regionalism;
        List<Regionalism> regionalismList = new ArrayList<>();
        String parentCode;
        byte[] bytes;
        JSONObject jsonObject;
        for (File file : files) {
            parentCode = file.getName().substring(0, 6);
            try {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        regionalismMapper.insertBatch(regionalismList);
        return "success, 插入" + regionalismList.size() + "条!";
    }
}
