package com.example.demo;

import com.example.demo.transactional.TransactionManager;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.sql.SQLException;

public class ConnectionTest {
    @Test
    public void connectTest(){
        try {
            DataSourceUtils.getConnection(TransactionManager.getDataSource());
        } catch (SQLException throwables) {
            System.out.println("connect Error");
        }
    }
}
