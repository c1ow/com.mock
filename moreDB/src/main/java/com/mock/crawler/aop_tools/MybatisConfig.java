package com.mock.crawler.aop_tools;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 11273 on 2017/7/7.
 * 重写sqlSessionFactory
 */
@Configuration
@ConditionalOnClass({EnableTransactionManagement.class})
@Import(DataSourceConfiguration.class)
@MapperScan(basePackages={"com.mock.crawler.dao"})
public class MybatisConfig extends MybatisAutoConfiguration{

    private final Logger logger = LoggerFactory.getLogger(MybatisConfig.class);
    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;
    @Value("${spring.datasource.readSize}")
    private String dataSourceSize;
    @Resource(name = "writeDataSource")
    private DataSource writeDataSource;
    @Resource(name = "readDataSource1")
    private DataSource readDataSource1;

    /**
     * 重载父类
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        logger.info("-------------------------重载父类 sqlSessionFactory init-----------------------"+dataSourceSize);
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(roundRobinDataSourceProxy());
//        sqlSessionFactoryBean.setTypeAliasesPackage("com.mock.crawler.Model");
//        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/com/mock/crawler/mapper/*.xml"));
//        sqlSessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return super.sqlSessionFactory(roundRobinDataSourceProxy());
//        return sqlSessionFactoryBean.getObject();
    }
    /**
     * 有多少个数据源就要配置多少个bean
     * @return
     */
    @Bean
    public AbstractRoutingDataSource roundRobinDataSourceProxy() {
        int size = Integer.parseInt(dataSourceSize);
        MyAbstractRoutingDataSource proxy = new MyAbstractRoutingDataSource(size);
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        targetDataSources.put(DataSourceType.master.getType(), writeDataSource);

//        for (int i = 0; i < size; i++) {
//        }
        targetDataSources.put(0,readDataSource1 );
        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(targetDataSources);
        return proxy;
    }
}
