package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RequiredArgsConstructor
@Controller
public class bookController {

    final private bookRepository bookRepository;

    final private bookStoreR bookStoreR;

    @GetMapping("/test")
    @ResponseBody
    public void bookReturn(){
        List<book> books = bookRepository.findAll();
        for (book book : books) {
            bookstore bookstore = book.getBookstore();
            System.out.println(bookstore.getName());
        }

    }
    @GetMapping("/test2")
    @ResponseBody
    public void bookReturn2(){
        List<bookstore> bookstores=bookStoreR.findAll();
        /*int cnt = 0;
        for ( bookstore bookstore : bookstores) {
            List<book> books = bookstores.get(cnt).getBookSet();
            cnt++;
            for (book book : books) {
                System.out.println(book.getName());
            }
        }*/
    }
}
