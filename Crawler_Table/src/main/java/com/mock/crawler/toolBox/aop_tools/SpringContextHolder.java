package com.mock.crawler.toolBox.aop_tools;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.activation.DataSource;

/**
 * Created by C1ow on 2017/7/8.
 */
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware,DisposableBean {
//public class SpringContextHolder {
    private static ApplicationContext applicationContext;
    @Override
    public  void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext=applicationContext;
//        System.err.println("--->获取的上下文"+JSON.toJSONString(applicationContext,true));
    }
    @SuppressWarnings("unchecked")
    public static DataSource getBean(String name) {
        System.err.println("---->"+ JSON.toJSONString(applicationContext,true));
        return (DataSource) applicationContext.getBean(name);
    }

    @Override
    public void destroy() throws Exception {
    }


}
