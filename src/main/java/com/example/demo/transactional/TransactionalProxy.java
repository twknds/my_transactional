package com.example.demo.transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public abstract class TransactionalProxy implements TransactionalInterface{
    public String transactionMethodName;
    PlatformTransactionManager platformTransactionManager;
    TransactionStatus status;

    Connection conn;
    {
        try {
            platformTransactionManager = new DataSourceTransactionManager(TransactionManager.getDataSource());
            status =platformTransactionManager.getTransaction(new DefaultTransactionAttribute());
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
        platformTransactionManager.commit(status);
    }
    @Override
    public void logic(){
        before();
        try {
            runLogic();
            after();
        }catch (Exception e){
            platformTransactionManager.rollback(status);
        }
    }
    @Override
    public void noLogic() throws SQLException{
        conn.setAutoCommit(true);
        runLogic();
    }
    public void runLogic(){
        try{
            Method method = this.getClass().getMethod(transactionMethodName,null);
            method.invoke(this,null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
