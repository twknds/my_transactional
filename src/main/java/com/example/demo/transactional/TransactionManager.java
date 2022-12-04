package com.example.demo.transactional;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;
import com.zaxxer.hikari.pool.HikariPool;
import com.zaxxer.hikari.util.ConcurrentBag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

public class TransactionManager {
    private static final String url = "jdbc:h2:tcp://localhost:9092/~/test";

    private static String name = "sa";


    private static String pass = "";

    private static final long CONNECTION_TIMEOUT = SECONDS.toMillis(30);
    private static final long VALIDATION_TIMEOUT = SECONDS.toMillis(5);
    private static final long SOFT_TIMEOUT_FLOOR = Long.getLong("com.zaxxer.hikari.timeoutMs.floor", 250L);
    private static final long IDLE_TIMEOUT = MINUTES.toMillis(10);
    private static final long MAX_LIFETIME = MINUTES.toMillis(30);
    private static final long DEFAULT_KEEPALIVE_TIME = 0L;
    private static final int DEFAULT_POOL_SIZE = 10;
    public static DataSource getDataSource() throws SQLException {
        return new DriverManagerDataSource(url,name,pass);
    }
}
