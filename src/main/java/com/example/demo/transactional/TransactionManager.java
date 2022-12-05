package com.example.demo.transactional;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final String url = "jdbc:h2:tcp://localhost:9092/~/test";
    private static String name = "sa";
    private static String pass = "";
    public static Connection connection = null;
    private final static DriverManagerDataSource ds = new DriverManagerDataSource(url,name,pass);
    public static DataSource getDataSource() throws SQLException {
        return ds;
    }
}
