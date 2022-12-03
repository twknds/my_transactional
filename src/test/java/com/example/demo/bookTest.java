package com.example.demo;


import com.example.demo.domain.book;

import com.example.demo.domain.bookRepository;
import com.example.demo.domain.bookStoreR;
import com.example.demo.domain.bookstore;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class bookTest {
    @Autowired
    bookRepository bookRepository;

    @Autowired
    bookStoreR bookStoreR;

    Logger logger = LoggerFactory.getLogger(bookTest.class);
    @Test
    public void addBook(){
        List<book> book = new ArrayList<>();
        bookstore bookstroe = new bookstore();
        bookstroe.setName("책방");
        bookStoreR.save(bookstroe);
        for (int i =0;i<10;i++){
            book.add(new book().setName("bookname"+i));
            bookstroe.add(book.get(i));
            bookRepository.save(book.get(i));
        }
    }
    @Test
    public void n1ProblemT(){
        //bookstore newbookStore = bookStoreR.getReferenceById(2);
        //book books = bookRepository.getReferenceById(3);
//        Hibernate.unproxy(newbookStore);
        //String name = newbookStore.getName();
        //String name = books.getName();
        //System.out.println(name);
        //List<bookstore> bookstores=bookStoreR.findAll();
        //Optional<bookstore> bookstore =bookStoreR.findById(1);
        //bookRepository.findAll();
        bookStoreR.findAll();
        /*for (bookstore books: bookstores){
            System.out.println(books.getName());
        }
        List<book> books = bookRepository.findAll();*/
    }
    @Test
    public void addBookS(){
        bookstore bookstroe = new bookstore();
        bookstroe.setName("책방3");
        bookStoreR.save(bookstroe);
    }
}
