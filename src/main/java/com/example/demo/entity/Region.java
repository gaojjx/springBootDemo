package com.example.demo.entity;

import java.util.List;

public class Region {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.id
     *
     * @mbg.generated Tue Sep 26 11:25:03 CST 2017
     */
    private String id;


    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.region_type
     *
     * @mbg.generated Tue Sep 26 11:25:03 CST 2017
     */
    private Integer regionType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.parent_id
     *
     * @mbg.generated Tue Sep 26 11:25:03 CST 2017
     */
    private String parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.name
     *
     * @mbg.generated Tue Sep 26 11:25:03 CST 2017
     */
    private String name;

    private List<Region> list;
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column region.region_code
     *
     * @mbg.generated Tue Sep 26 11:25:03 CST 2017
     */
    private String regionCode;

    private String groupId;

    private Integer size;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getRegionType() {
        return regionType;
    }

    public void setRegionType(Integer regionType) {
        this.regionType = regionType;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Region> getList() {
        return list;
    }

    public void setList(List<Region> list) {
        this.list = list;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}