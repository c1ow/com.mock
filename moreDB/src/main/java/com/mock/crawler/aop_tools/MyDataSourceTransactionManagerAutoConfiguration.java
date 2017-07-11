package com.mock.crawler.aop_tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 重写事务配置
 * @author:gaoguangjin
 * @date 2016/5/31 12:27
 */
@Configuration
@EnableTransactionManagement
public class MyDataSourceTransactionManagerAutoConfiguration extends DataSourceTransactionManagerAutoConfiguration {

	private final Logger logger = LoggerFactory.getLogger(MyDataSourceTransactionManagerAutoConfiguration.class);
	@Resource(name = "writeDataSource")
	private DataSource dataSource;
	/**
	 * 自定义事务
	 * MyBatis自动参与到spring事务管理中，无需额外配置，只要org.mybatis.spring.SqlSessionFactoryBean引用的数据源与DataSourceTransactionManager引用的数据源一致即可，否则事务管理会不起作用。
	 * @return
	 */
	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManagers() {
		logger.info("-------------------- transactionManager init ---------------------");
		return new DataSourceTransactionManager(dataSource);
	}
}
