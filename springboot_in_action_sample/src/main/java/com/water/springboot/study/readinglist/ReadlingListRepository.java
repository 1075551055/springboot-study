package com.water.springboot.study.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReadlingListRepository  extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
