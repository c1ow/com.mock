package com.mock.crawler.aop_tools;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * Created by 11273 on 2017/7/7.
 * AOP拦截本地线程
 */
@Aspect
@Component
public class AOPUtil {
    //创建日志
    private final Logger logger = LoggerFactory.getLogger(AOPUtil.class);

    /**
     * 查询切点
     */
    @Pointcut("execution(* com.mock.crawler.dao..*.find*(..) )")
    public void find(){}

    /**
     * 更新切点
     */
    @Pointcut("execution(* com.mock.crawler.dao..*.insert*(..))")
    public void update(){}

    /**
     * 具体实现
     */
    @Before("find()")
    public void findAOP(){
        DataSourceContextHolder.slave();
        System.err.println("这是一个查询操作！！！");
        logger.info("这是一个查询操作！！！");

    }
    @Before("update()")
    public void updateAOP(){
        DataSourceContextHolder.master();
        System.err.println("这是一个更新操作！！！");
        logger.info("这是一个更新操作！！！");
    }
}
