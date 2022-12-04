package com.example.demo.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
public class bookstore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "bookstore",fetch = FetchType.EAGER)
    private List<book> bookSet = new ArrayList<>();

    public void add(book book){
        book.setBookstore(this);
        bookSet.add(book);
    }
}
