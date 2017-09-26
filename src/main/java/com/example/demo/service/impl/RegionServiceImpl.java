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
        String groupId;
        //查询第二级
        for (Region secondRegion : secondRegionList) {
            searchRegion = getRegionParam(secondRegion);
            List<Region> thirdRegionList = findRegion(searchRegion);
            //查询第三级
            for (Region thirdRegion : thirdRegionList) {
                searchRegion = getRegionParam(thirdRegion);
                List<Region> lastLevelRegionList = findRegion(searchRegion);
                //查询第四级
                if (lastLevelRegionList != null && lastLevelRegionList.size() > 0) {
                    groupId = lastLevelRegionList.get(0).getId();
                    thirdRegion.setGroupId(groupId.substring(0, groupId.length() - 2));
                    thirdRegion.setSize(lastLevelRegionList.size());
                }
            }
            secondRegion.setList(thirdRegionList);
        }
        root.setList(secondRegionList);
        return root;
    }

    /**
     * @param region
     * @return
     */
    private Region getRegionParam(Region region) {
        Region param = new Region();
        param.setRegionType(region.getRegionType() << 1);
        param.setParentId(region.getRegionCode());
        return param;
    }
}
