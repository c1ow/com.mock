package com.mock.crawler.aop_tools;

import jdk.nashorn.internal.objects.annotations.Getter;

/**
 * Created by 11273 on 2017/7/7.
 * 数据库类型枚举
 */
public enum DataSourceType {
    master("write","主库"), slave("read","从库");

    private String type;

    private String name;

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }
}
