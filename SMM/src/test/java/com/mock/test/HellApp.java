package com.mock.test;

import com.alibaba.fastjson.JSON;
import com.mock.model.Collection;
import com.mock.model.MockModel;
import com.mock.model.SonBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by 11273 on 2017/7/11.
 */
public class HellApp {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Beans.xml");
        MockModel mockModel = (MockModel) applicationContext.getBean("mockModel");
        String mockModelMassag = mockModel.getMessage();
        System.err.println("-------->>>>首次测试Spring——————>"+mockModelMassag);
        SonBean sonBean = (SonBean) applicationContext.getBean("sonBean");
        System.err.println("------->>>SonBean-->info："+ JSON.toJSONString(sonBean,true));
        Thread.sleep(1000);
//        依赖注入的测试
        System.out.print("依赖注入的测试：");
        mockModel.sonBeanInfo();
//        不使构造方法注入
        mockModel.mockAutoPrin();
//        注入集合
        Collection collection = (Collection) applicationContext.getBean("collection");
        System.out.println("------------->"+collection.getJsonObject());
//       Beans的自动注入
//        byName
        System.out.println("---------->byName"+mockModel.getCollection());
    }
}
