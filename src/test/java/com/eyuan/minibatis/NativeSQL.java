package com.eyuan.minibatis;

import java.sql.*;

/**
 * 原生SQL回顾
 */
public class NativeSQL {
    public static void main(String[] args) {
        try {
            //加载驱动、初始化连接信息
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://192.168.10.105:3306/z_test?useSSL=false",
                    "root",
                    "root");
            //SQL查询语句
            String sql = "select * from user;";
            //创建声名
            Statement statement = connection.createStatement();
            //执行SQL
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println("resultSet = "+resultSet);
            //移动游标
            resultSet.next();

            String row = resultSet.toString();
            System.out.println("row col = "+row);

            //获取结果第1列
            System.out.println("resultSet column 1 = "+resultSet.getString(1));
            //获取结果第2列
            System.out.println("resultSet column 2 = "+resultSet.getString(2));

            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println("getColumnCount = "+resultSetMetaData.getColumnCount());
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                System.out.println("MetaData.getColumn = "+resultSetMetaData.getColumnName(i));
                System.out.println("MetaData.getColumnType = "+resultSetMetaData.getColumnType(i));
                System.out.println("MetaData.getColumnClassName = "+resultSetMetaData.getColumnClassName(i));
                System.out.println("MetaData.getTableName = "+resultSetMetaData.getTableName(i));
            }
            //关闭连接
            connection.close();

        } catch (Exception e) {
            System.out.println("SQL error:"+e);
            e.printStackTrace();
        }
    }
}
