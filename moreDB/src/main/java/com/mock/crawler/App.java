package com.mock.crawler;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by 11273 on 2017/7/4.
 */

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ApplicationContext app = SpringApplication.run(App.class, args);
//        if (app!=null) System.err.println("获取上下文");

    }
}
