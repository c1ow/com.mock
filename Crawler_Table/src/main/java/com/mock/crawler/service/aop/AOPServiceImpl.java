package com.mock.crawler.service.aop;

import com.alibaba.fastjson.JSONObject;
import com.mock.crawler.dao.aop.AOPDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 11273 on 2017/7/7.
 */
@Service
public class AOPServiceImpl implements AOPService {
    @Autowired
    private AOPDao aopDao;
    @Override
    public int insert(JSONObject jsonObject) {
        return aopDao.insert(jsonObject);
    }

    @Override
    public JSONObject findById(JSONObject jsonObject) {
        return aopDao.findById(jsonObject);
    }
}
