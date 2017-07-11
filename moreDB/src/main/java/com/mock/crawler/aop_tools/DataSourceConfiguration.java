package com.mock.crawler.aop_tools;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * Created by 11273 on 2017/7/7.
 * 解析配置项
 */
@Configuration
public class DataSourceConfiguration {
    private final Logger logger = LoggerFactory.getLogger(DataSourceConfiguration.class);
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 初始化主表
     * @return
     */
    @Bean(name = "writeDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.write")
    public DataSource writeDataSource(){
        logger.info("-----------------------初始化主表-------------------------");
        DataSourceBuilder type = DataSourceBuilder.create().type(dataSourceType);
        DataSource dataSource=type.build();
        return dataSource;
    }

    /**
     * 初始化从表
     * @return
     */
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "spring.datasource.read1")
    public DataSource readDataSource1(){
        logger.info("-----------------------初始化从表-------------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

}
