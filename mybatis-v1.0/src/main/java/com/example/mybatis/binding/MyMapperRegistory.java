package com.example.mybatis.binding;

import com.example.mybatis.session.MySqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:42:24
 */
public class MyMapperRegistory {

    /**
     * 保存所有mapper接口
     */
    private final Map<Class<?>, MapperProxyFactory> knownMappers = new HashMap<>();

    /**
     * Configuration解析anontation之后调用方法初始化所有mapper
     */
    public <T> void addMapper(Class<T> clazz){
        knownMappers.put(clazz, new MapperProxyFactory(clazz));
    }

    /**
     * getMapper最底层执行者，获取mapper的MapperProxyFactory对象
     */
    public <T> T getMapper(Class<T> clazz, MySqlSession sqlSession) {
        MapperProxyFactory proxyFactory = knownMappers.get(clazz);
        if (proxyFactory == null) {
            throw new RuntimeException("Type: " + clazz + " can not find");
        }
        return (T)proxyFactory.newInstance(sqlSession);
    }
}
