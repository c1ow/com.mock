package com.mock.crawler.toolBox.aop_tools;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.activation.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11273 on 2017/7/7.
 * 重写sqlSessionFactory
 */
@Configuration
@AutoConfigureAfter({DataSourceConfiguration.class})
@Component
public class MybatisConfig extends MybatisAutoConfiguration{
    private final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);
    @Value("${datasource.readSize}")
    private String dataSourceSize;

    /**
     * 重载父类
     * @return
     */
    @Bean
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        logger.info("-------------------------重载父类 sqlSessionFactory init-----------------------");

        return super.sqlSessionFactory(roundRobinDataSourceProxy());
    }
    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean
//    @Order(3)
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {
        int size = Integer.parseInt(dataSourceSize);
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        DataSource writeDataSource = SpringContextHolder.getBean("masterDataSource");
        // 主库
        targetDataSources.put(DataSourceType.master.getType(), SpringContextHolder.getBean("masterDataSource"));

        for (int i = 0; i < size; i++) {
            targetDataSources.put(i, SpringContextHolder.getBean("slaveDataSource" + (i + 1)));
        }
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}
