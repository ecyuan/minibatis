package com.eyuan.minibatis.proxy;

import com.eyuan.minibatis.annotation.Select;
import com.eyuan.minibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * mapper代理处理器 - 间接调用mapper的方法
 */
public class MapperInvocationHandler implements InvocationHandler {
    private SqlSession sqlSession;
    private Class<?> mapperInterface;
    public MapperInvocationHandler(Class<?> mapperInterface, SqlSession sqlSession) {
        this.mapperInterface = mapperInterface;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("MapperInvocationHandler.invode exec, proxy = "
                +proxy.getClass().getName()+", method ="+method.getName()
                +", args = "+args.toString()
        );

        //根据mapperInterface（接口）方法method来获取其注解上的SQL 或 相应的XML上的SQL(这里现在没做）
        String sql = method.getAnnotation(Select.class).value();
        System.out.println("获取SQL语句: "+sql);
        //替换占位符号
        sql = sql.replaceAll("#", String.valueOf(args[0]));
        System.out.println("解析后的SQL语句: " + sql);

        System.out.println("开始JDBC的原生化执行SQL");
        //开始JDBC的原生化执行SQL
        Statement statement = sqlSession.getConnection().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        String result = null;
        //这里只获取第一个字段值
        if (resultSet != null) {
            result = resultSet.getString(1);
            System.out.println("resultSet = "+result);
        }

        //处理查询结果并返回 - 这里可以加上回调handler - 加缓存等等
        System.out.println("处理返回结果并最终返回数据....");

        //返回类型处理 - 这里简化为字符串
        return result;
    }
}
