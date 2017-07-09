package com.mock.crawler.service;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * Created by 11273 on 2017/7/4.
 */
public interface TableService {
    void insert(JSONObject jsonObject) throws IOException;
}
