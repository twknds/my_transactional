package com.example.demo.transactional;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TransactionManager {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String name;

    @Value("${spring.datasource.password}")
    private String pass;
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,name,pass);
    }
}
