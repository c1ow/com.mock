package com.mock.crawler.toolBox.aop_tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by 11273 on 2017/7/7.
 * 解析配置项
 */
@Configuration
@Component
public class DataSourceConfiguration {
    private final Logger logger = LoggerFactory.getLogger("");
    @Value("${datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 初始化主表
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "${datasource.write}")
    @Order(1)
    public DataSource masterDataSource(){
        logger.info("-----------------------初始化主表-------------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 初始化从表
     * @return
     */
    @Bean(name = "slaveDataSource1")
//    @Primary
    @ConfigurationProperties(prefix = "${datasource.read1}")
    @Order(2)
    public DataSource slaveDataSource(){
        logger.info("-----------------------初始化从表-------------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
