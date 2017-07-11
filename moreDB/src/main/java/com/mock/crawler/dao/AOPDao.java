package com.mock.crawler.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by 11273 on 2017/7/10.
 */
@Mapper
public interface AOPDao {
    int insert(JSONObject jsonObject);
    JSONObject findById(JSONObject jsonObject);
}
