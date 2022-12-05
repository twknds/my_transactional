package com.example.demo.domain;

import lombok.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
}
