package com.example.mybatis.executor;


import com.example.sys.domain.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @description: TODO
 * @author: bianqipeng
 * @date: 2021-02-23 10:29:58
 */
public class MyDefaultExecutor implements MyExecutor {

    @Override
    public <T> T query(String statement, String parameter) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        User user = null;
        try {
            conn = getConnection();

            //TODO ParameterHandler
            preparedStatement = conn.prepareStatement(String.format(statement, Integer.parseInt(parameter)));
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getResultSet();

            //TODO ObjectFactory
            user = new User();

            //TODO ResultSetHandler
            while (rs.next()) {
                user.setId(rs.getLong(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T)user;
    }

    /**
     * Getter: MyDefaultExecutor.getConnection
     * @return: Connection
     * @throws: SQLException
     */
    public Connection getConnection() throws SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://192.168.11.136:3306/handle-framework?useUnicode=true&characterEncoding=UTF-8";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            // classLoader,加载对应驱动
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
