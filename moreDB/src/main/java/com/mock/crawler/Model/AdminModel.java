package com.mock.crawler.Model;

import java.util.List;

/**
 * Created by 11273 on 2017/7/6.
 */
public class AdminModel {
    private String areaName;
    private String areaId;
    private List<AdminModel> cities;//市
    private List<AdminModel> counties;//区


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public List<AdminModel> getCities() {
        return cities;
    }

    public void setCities(List<AdminModel> cities) {
        this.cities = cities;
    }

    public List<AdminModel> getCounties() {
        return counties;
    }

    public void setCounties(List<AdminModel> counties) {
        this.counties = counties;
    }
}
