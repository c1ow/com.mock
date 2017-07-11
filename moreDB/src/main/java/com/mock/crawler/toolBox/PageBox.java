package com.mock.crawler.toolBox;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * Created by 11273 on 2017/7/5.
 */
public class PageBox<T> {
    public Page<T> getPage(JSONObject jsonObject){
        Integer pageNum=1;
        Integer pageSize=5000;
        if(jsonObject.containsKey("pageNum")){
            pageNum=jsonObject.getInteger("pageNum");
        }
        if(jsonObject.containsKey("pageSize")){
            pageSize=jsonObject.getInteger("pageSize");
        }
        Page<T> page= PageHelper.startPage(pageNum,pageSize);
        return page;
    }
}
