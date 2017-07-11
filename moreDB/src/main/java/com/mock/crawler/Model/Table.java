package com.mock.crawler.Model;

/**
 * Created by 11273 on 2017/7/4.
 */
public class Table {
    private int id;
    private String administrativeDistrict;
    private String areaNumber;
    private String type;
    private String url;
    private String parent;
    private String typeCode;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdministrativeDistrict() {
        return administrativeDistrict;
    }

    public void setAdministrativeDistrict(String administrativeDistrict) {
        this.administrativeDistrict = administrativeDistrict;
    }

    public String getAreaNumber() {
        return areaNumber;
    }

    public void setAreaNumber(String areaNumber) {
        this.areaNumber = areaNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

