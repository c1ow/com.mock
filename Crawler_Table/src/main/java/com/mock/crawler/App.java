package com.mock.crawler;

import com.mock.crawler.toolBox.aop_tools.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 11273 on 2017/7/4.
 */
@SpringBootApplication
@MapperScan("com.mock.crawler.dao")
public class App {
    public static void main(String[] args) {
      ApplicationContext applicationContext = SpringApplication.run(App.class,args);
      if (applicationContext!=null)
        System.err.println("----->>>>");
//        SpringContextHolder.setApplicationContext(applicationContext);
    }
}
