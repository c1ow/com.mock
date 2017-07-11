package com.mock.crawler.aop_tools;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 11273 on 2017/7/7.
 * 多数据源切换
 */
public class MyAbstractRoutingDataSource extends AbstractRoutingDataSource {

    private final int dataSourceNumber;
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public MyAbstractRoutingDataSource(int dataSourceNumber) {
        this.dataSourceNumber = dataSourceNumber;
    }
    @Override
    protected Object determineCurrentLookupKey() {
        String typeKey = DataSourceContextHolder.getJdbcType();
        if (typeKey.equals(DataSourceType.master.getType()))
            return DataSourceType.master.getType();
        //读 简单的负载均衡
        int number = atomicInteger.getAndAdd(1);
        int lookupKey = number%dataSourceNumber;
        return new Integer(lookupKey);
    }
}
