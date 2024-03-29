package com.oaec.shoes.util;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcDataSource {
    private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USERNAME = "ZZQ";
    private static final String PASSWORD = "123456";
    private static DruidDataSource dataSource;
    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }
    public static DruidDataSource getDataSource(){
        return dataSource;
    }
}
