package com.mock.crawler.controller;

import com.alibaba.fastjson.JSONObject;
import com.mock.crawler.service.PrintLogService;
import com.mock.crawler.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by 11273 on 2017/7/4.
 */
@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableService tableService;
    @Autowired
    private PrintLogService printLogService;
    @PostMapping("/insert")
    public String insert(@RequestBody JSONObject jsonObject) throws IOException {
        String[] ss = {"flag","parent","type"};
        for (String s:ss) {
            if (!jsonObject.containsKey(s)) return "缺少参数"+s;
        }
        tableService.insert(jsonObject);
        return null;
    }
    @PostMapping("/printLog")
    public String printLog(){
        System.err.println("------>>>>连接数据库------>>>>");
        return printLogService.printLog();
    }
}
