package com.mock.crawler.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 11273 on 2017/7/8.
 */
public interface AOPService {
    int insert(JSONObject jsonObject);
    JSONObject findById(JSONObject jsonObject);
}
