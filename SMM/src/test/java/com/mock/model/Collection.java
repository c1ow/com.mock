package com.mock.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 11273 on 2017/7/14.
 */
public class Collection {
    private List list;
    private Set set;
    private JSONObject jsonObject;
    private Map map;

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public Set getSet() {
        return set;
    }

    public void setSet(Set set) {
        this.set = set;
    }

    public String getJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("list",getList());
        jsonObject.put("set",getSet());
        jsonObject.put("map",getMap());
        return JSON.toJSONString(jsonObject,true);
    }

    public void setJsonObject(JSONObject jsonObject) {
        jsonObject.put("list",getList());
        jsonObject.put("set",getSet());
        jsonObject.put("map",getMap());
        this.jsonObject = jsonObject;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }
}
