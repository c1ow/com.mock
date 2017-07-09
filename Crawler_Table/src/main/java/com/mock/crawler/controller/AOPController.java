package com.mock.crawler.controller;

import com.alibaba.fastjson.JSONObject;
import com.mock.crawler.service.aop.AOPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 11273 on 2017/7/7.
 */
@RestController
@RequestMapping("/aop")
public class AOPController {
    @Autowired
    private AOPService aopService;
    @Value("${datasource.readSize}")
    private String readSize;
    /**
     * 添加
     * @param jsonObject
     * @return
     */
    @PostMapping("/insert")
    public String insert(@RequestBody JSONObject jsonObject){
        int res = aopService.insert(jsonObject);
       // DataSourceConflg dataSourceConflg = new DataSourceConflg();
        System.err.println("--->"+readSize);
        if (res>0) return "添加成功";
        return "呵呵，添加失败了";
    }
    @PostMapping("findById")
    public JSONObject findById(@RequestBody JSONObject jsonObject){
        return aopService.findById(jsonObject);
    }
}
