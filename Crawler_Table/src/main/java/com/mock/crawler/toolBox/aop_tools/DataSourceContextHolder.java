package com.mock.crawler.toolBox.aop_tools;

/**
 * Created by C1ow on 2017/7/7.
 * 本地线程全局变量
 */
public class DataSourceContextHolder {
    //获取本地线程
    private static final ThreadLocal<String> local = new ThreadLocal<String>();

    public static ThreadLocal<String> getLocal(){
        return local;
    }

    /**
     * 从库有很多个
     */
    public static void slave(){
        local.set(DataSourceType.slave.getType());
    }
    /**
     * 主库只有一个
     */
    public static void master(){
        local.set(DataSourceType.master.getType());
    }

    /**
     * 获取jdbcType
     * @return
     */
    public static String getJdbcType(){
        return local.get();
    }
}
