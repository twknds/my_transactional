package com.example.demo.domain;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.sql.SQLException;

class bookRepositoryTest {
    bookRepository bookRepository = new bookRepository();
    Logger logger = LoggerFactory.getLogger(bookRepositoryTest.class);
    @Test
    public void saveTest() throws SQLException {
        book book = new book();
        book.setId(1);
        book.setName("1");
        System.out.println(book.getId());
        System.out.println(bookRepository.save(book).getName());
        //System.out.println(bookRepository.findByName("1"));
    }
    @Test
    public void findTest() throws SQLException{
        System.out.println(bookRepository.findByName("1"));
    }
    @Test
    public void deleteTest() throws SQLException{
        bookRepository.delete(2);
    }
}