package com.eyuan.minibatis.session;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 数据库连接
 */
public class DefaultSqlSession implements SqlSession {

    private DataSource dataSource;

    private Connection connection;

    /**
     * 初始化时带入连接信息 - 可以是各种第三方连接库，方便扩展
     * @param dataSource
     */
    public DefaultSqlSession(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 获取数据库连接 - 这里线程不安全，忽略先
     * @return
     */
    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            this.connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("sql error: "+e.getMessage());
            return null;
        }
        return this.connection;
    }
}