package com.eyuan.minibatis.session;

import java.sql.Connection;

public interface SqlSession {
    Connection getConnection();
}
