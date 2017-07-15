package com.mock.model;

/**
 * Created by 11273 on 2017/7/13.
 */
public class SonBean {
    private String message1;
    private String message2;
    private String message3;

    public SonBean(){
        System.out.println("------>创建SonBean");
    }
    public void info(){
        System.out.println("------->>>依赖注入");
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public String getMessage3() {
        return message3;
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
    }
}
