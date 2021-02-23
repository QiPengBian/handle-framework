package com.example.mybatis.session;

import com.example.mybatis.executor.MyExecutor;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:23:51
 */
public class MySqlSession {

    private MyConfiguration configuration;

    private MyExecutor executor;

    /**
     * 用构造器将两个对象形成关系
     */
    public MySqlSession(MyConfiguration configuration, MyExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    public MyConfiguration getConfiguration() {
        return configuration;
    }

    /**
     * 委派configuration获取mapper
     */
    public <T> T getMapper(Class<T> clazz){
        return configuration.getMapper(clazz, this);
    }

    /**
     * 委派executor查询
     */
    public <T> T selectOne(String statement, String parameter){
        return executor.query(statement, parameter);
    }
}
