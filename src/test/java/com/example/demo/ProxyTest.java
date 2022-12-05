package com.example.demo;

import com.example.demo.domain.bookRepository;
import com.example.demo.transactional.UseService;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

public class ProxyTest{
    bookRepository bookRepository = new bookRepository();
    // TransactionalProxy abstract클래스를 상속받는 transactional기능을 적용받을 메서드를 정해서 적용한다.

    // Transaction처리를 못받게 비즈니스로직만을 실행시킨다.
    @Test
    public void noTransactionTest() throws RuntimeException ,SQLException{
        new UseService("getError").noLogic();
    }
    // before, after를 다받아서 transaction처리를 받게한다.
    @Test
    public void transactionTest() throws SQLException{
        new UseService("getError").logic();
    }
    @Test
    public void proxyTest(){
        new UseService("noError").logic();
    }
}
