package com.mock.crawler.dao.aop;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by C1ow on 2017/7/9.
 */
@Mapper
public interface AOPDao {
        int insert(JSONObject jsonObject);
        JSONObject findById(JSONObject jsonObject);
}
