package com.example.mybatis.session;

import com.example.mybatis.binding.MyMapperRegistory;
import com.example.sys.mapper.UserMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:26:22
 */
public class MyConfiguration {

    public final MyMapperRegistory mapperRegistory = new MyMapperRegistory();

    public static final Map<String, String> mappedStatements = new HashMap<>();

    public MyConfiguration() {
        mapperRegistory.addMapper(UserMapper.class);
        mappedStatements.put("com.example.sys.mapper.UserMapper.selectByPrimaryKey"
                , "select * from sys_user where id = %d");
    }

    /**
     * MapperProxy根据statementName查找是否有对应SQL
     */
    public boolean hasStatement(String statementName) {
        return mappedStatements.containsKey(statementName);
    }

    /**
     * MapperProxy根据statementID获取SQL
     */
    public String getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public <T> T getMapper(Class<T> clazz, MySqlSession sqlSession) {
        return mapperRegistory.getMapper(clazz, sqlSession);
    }
}
