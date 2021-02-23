package com.example.mybatis.binding;

import com.example.mybatis.session.MyConfiguration;
import com.example.mybatis.session.MySqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:44:24
 */
public class MyMapperProxy implements InvocationHandler {

    private MySqlSession sqlSession;

    public MyMapperProxy(MySqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    /**
     * 每一个Mapper的每个方法都将执行invoke方法，此方法判断方法名是否维护在Configuration中，如有则取出SQL
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MyConfiguration configuration = sqlSession.getConfiguration();
        String methodName = method.getDeclaringClass().getName() + "." + method.getName();
        if (configuration.hasStatement(methodName)) {
            String sql = configuration.getMappedStatement(methodName);
            return sqlSession.selectOne(sql, args[0].toString());
        }
        return method.invoke(proxy, args);
    }
}
