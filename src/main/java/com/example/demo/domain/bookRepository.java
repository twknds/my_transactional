package com.example.demo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookRepository extends JpaRepository<book,Integer> {

}
