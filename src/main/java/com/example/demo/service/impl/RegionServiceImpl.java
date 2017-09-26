package com.example.demo.service.impl;

import com.example.demo.entity.Region;
import com.example.demo.mapper.RegionMapper;
import com.example.demo.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class RegionServiceImpl implements RegionService{
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public List<Region> findRegion(Region region) {
        return regionMapper.findRegion(region);
    }

    @Override
    public Region start() {
        Region searchRegion = new Region();
        Region root = regionMapper.selectByPrimaryKey("51162300000000000000000000028813");
        searchRegion = getRegionParam(root);
        List<Region> secondRegionList = findRegion(searchRegion);
        for (Region secondRegion : secondRegionList) {
            searchRegion = getRegionParam(secondRegion);
            List<Region> thirdRegionList = findRegion(searchRegion);
            for (Region thirdRegion : thirdRegionList) {
                searchRegion = getRegionParam(thirdRegion);
                thirdRegion.setList(findRegion(searchRegion));
//                List<LastLevelRegion> lastLevelRegionList = regionMapper.findLastLevelRegion(searchRegion);
            }
            secondRegion.setList(thirdRegionList);
        }
        root.setList(secondRegionList);
        return root;
    }

    Region getRegionParam(Region region) {
        Region param = new Region();
        param.setRegionType(region.getRegionType() << 1);
        param.setParentId(region.getRegionCode());
        return param;
    }
}
