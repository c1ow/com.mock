package com.mock.crawler.service;

import com.alibaba.fastjson.JSON;
import com.mock.crawler.Model.AdminModel;
import com.mock.crawler.dao.TableDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 11273 on 2017/7/6.
 */
@Service
public class PrintLogSreviceImpl implements PrintLogService{
    @Autowired
    private TableDao tableDao;
    //定义Log日志
    private static final Logger logger = LoggerFactory.getLogger(PrintLogService.class);
    @Override
    public String printLog() {
        try {
            //循环获取信息
            //1
            List<AdminModel> adminModels = tableDao.findAdminList("");
            for (AdminModel adminModel: adminModels){
                //2
                List<AdminModel> adminModels1 = tableDao.findAdminList(adminModel.getAreaId());
                for (AdminModel adminModel1: adminModels1) {
                    List<AdminModel> adminModels2 = tableDao.findAdminList(adminModel1.getAreaId());
                    //3
                    adminModel1.setCounties(adminModels2);
                }
                adminModel.setCities(adminModels1);
            }
//            System.err.println("---->"+ JSON.toJSONString(adminModels.get(1),true));
            logger.info(JSON.toJSONString(adminModels,true));
        }catch (Exception e){
            return "获取出错了！";
        }
        return "获取结束";
    }
}
