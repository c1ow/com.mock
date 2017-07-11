package com.mock.crawler.dao;

import com.alibaba.fastjson.JSONObject;
import com.mock.crawler.Model.AdminModel;
import com.mock.crawler.Model.Table;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by 11273 on 2017/7/4.
 */
@Mapper
public interface TableDao {
    List<Table> findUrlList(JSONObject jsonObject);
    void insert(Table table);
    //打印前三级的省市县
    List<AdminModel> findAdminList(@Param("areaId")String areaNumber);
}
