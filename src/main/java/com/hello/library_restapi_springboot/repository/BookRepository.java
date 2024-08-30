package com.hello.library_restapi_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.library_restapi_springboot.dto.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
 
}
