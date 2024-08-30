package com.hello.library_restapi_springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hello.library_restapi_springboot.dto.Book;
import com.hello.library_restapi_springboot.repository.BookRepository;

@Repository
public class BookDao {
    @Autowired
    BookRepository bookRepository;
	public Book findById(long bid) {
		return bookRepository.findById(bid).orElse(null);
	}

}
