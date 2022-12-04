package com.example.demo.transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.Connection;
import java.sql.SQLException;
@RequiredArgsConstructor
@Slf4j
public class TransactionalProxy implements TransactionalInterface {

    //private final PlatformTransactionManager platformTransactionManager;
    TransactionalInterface transactionalInterface = new TransactionalImpl();
    TransactionManager transactionManager = new TransactionManager();

    @Override
    public void logic() throws SQLException {
        // 호출전 처리기능 ex) commit 수동
        Connection conn = DataSourceUtils.getConnection(TransactionManager.getDataSource());
        try {
            conn.setAutoCommit(false);
            transactionalInterface.logic();
            conn.commit();
        }catch (SQLException e){
            conn.rollback();
            throw new IllegalStateException(e);
        }finally{
            release(conn);
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
