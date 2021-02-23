package com.example.mybatis.binding;

import com.example.mybatis.session.MySqlSession;

import java.lang.reflect.Proxy;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:44:42
 */
public class MapperProxyFactory<T> {

    private Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    public T newInstance(MySqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, new MyMapperProxy(sqlSession));
    }
}
