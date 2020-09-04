package com.eyuan.minibatis;

import com.eyuan.minibatis.datasource.MiniDataSource;
import com.eyuan.minibatis.mapper.OrderMapper;
import com.eyuan.minibatis.mapper.UserMapper;
import com.eyuan.minibatis.proxy.MapperProxyFactory;
import com.eyuan.minibatis.session.DefaultSqlSession;
import com.eyuan.minibatis.session.SqlSession;

public class TestMapper {
    public static void main(String[] args) {
        //加载数据库配置信息 - 方便理解这里我们简化配置过程 - 可以采用其他连接池如HiKari等
        MiniDataSource dataSource = new MiniDataSource();
        dataSource.setUseSSL(false);
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("test");
        dataSource.setUser("root");
        dataSource.setPassword("root");


        //生成连接会话信息
        SqlSession sqlSession = new DefaultSqlSession(dataSource);
        //生成mapper代理类 - 方便理解,这里也简化自动化加载mapper及其映射过程，在下面采用手工注入方式
        MapperProxyFactory factory = new MapperProxyFactory(sqlSession);

        UserMapper userMapper = factory.getMapper(UserMapper.class);
        String user = userMapper.getOne(888);
        System.out.println("SQL查询结果: "+user);

        OrderMapper orderMapper = factory.getMapper(OrderMapper.class);
        String order = orderMapper.getOrderId("10086");
        System.out.println("SQL查询结果: "+order);
    }
}
