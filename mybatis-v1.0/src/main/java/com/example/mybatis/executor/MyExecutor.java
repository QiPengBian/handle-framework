package com.example.mybatis.executor;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:28:04
 */
public interface MyExecutor {

    <T> T query(String statement, String param);
}
