package com.example.demo.domain;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    private bookstore bookstore;

    public book setName(String name) {
        this.name = name;
        return this;
    }
}
