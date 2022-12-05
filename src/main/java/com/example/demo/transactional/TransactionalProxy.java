package com.example.demo.transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public abstract class TransactionalProxy implements TransactionalInterface{
    public String transactionMethodName;
    Connection conn;
    {
        try {
            conn = DataSourceUtils.getConnection(TransactionManager.getDataSource());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public TransactionalProxy(String transactionMethodName){
        this.transactionMethodName = transactionMethodName;
    }
    private void before(){
        try {
            conn.setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private void after(){
        try {
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        release(conn);
    }
    @Override
    public void logic(){
        before();
        try {
            runLogic();
        }catch (Exception e){
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        after();
    }
    public void runLogic(){
        try{
            Method method = this.getClass().getMethod(transactionMethodName,null);
            method.invoke(this,null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    private void release(Connection conn){
        if (conn != null) {
            try {
                conn.setAutoCommit(true);//해당 커넥션을 바로 close()할 경우 setAutoCommit-false인채로 Pool로 돌아감
                conn.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
    }
}
