package com.eyuan.minibatis.proxy;

import com.eyuan.minibatis.session.SqlSession;

import java.lang.reflect.Proxy;

/**
 * Mapper生成工具-生成mapper的代理类
 */
public class MapperProxyFactory {
    private SqlSession sqlSession;

    public MapperProxyFactory(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public <T> T getMapper(Class<T> cls) {
        return (T) Proxy.newProxyInstance(cls.getClassLoader(),
                new Class[] {cls},
                new MapperInvocationHandler(cls, sqlSession)
        );
    }
}
