package com.example.demo.transactional;

import com.example.demo.domain.book;
import com.example.demo.domain.bookRepository;

import java.sql.SQLException;

public class UseService extends TransactionalProxy{
    bookRepository bookRepository = new bookRepository();
    public UseService(String transactionMethodName) {
        super(transactionMethodName);
    }
    public void noError() throws SQLException {
        book book1 = new book();
        book book2 = new book();
        book1.setName("1");
        book1.setId(1);
        book2.setName("2");
        book2.setId(2);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
    public void getError() throws SQLException {
        book book1 = new book();
        book book2 = new book();
        book1.setName("1");
        book1.setId(1);
        book2.setName("2");
        book2.setId(1);
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
