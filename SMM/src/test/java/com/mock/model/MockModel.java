package com.mock.model;

/**
 * Created by 11273 on 2017/7/11.
 */
public class MockModel {
    private String message;
    private MockAutoModel mockAutoModel;
    private Collection collection;
//    依赖注入
    private SonBean sonBean;
// 有参的构造方法
    public MockModel(SonBean sonBean) {
        this.sonBean = sonBean;
    }

    public MockModel(){
    System.out.println("----->创建MockModel");
}
    public void sonBeanInfo(){
        sonBean.info();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MockAutoModel getMockAutoModel() {
        return mockAutoModel;
    }

    public void setMockAutoModel(MockAutoModel mockAutoModel) {
        this.mockAutoModel = mockAutoModel;
    }
    public void mockAutoPrin(){
        mockAutoModel.mockPrin();
    }

    public String getCollection() {
        return collection.getJsonObject();
    }
    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}
