package com.mock.crawler.service.aop;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 11273 on 2017/7/7.
 */
public interface AOPService {
    int insert(JSONObject jsonObject);
    JSONObject findById(JSONObject jsonObject);
}
