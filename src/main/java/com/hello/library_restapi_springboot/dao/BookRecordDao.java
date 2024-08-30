package com.hello.library_restapi_springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;

import com.hello.library_restapi_springboot.dto.BookRecord;
import com.hello.library_restapi_springboot.repository.BookRecordRepository;

public class BookRecordDao {
    @Autowired
    BookRecordRepository bookRecordRepository;
	public void add(BookRecord record) {
		bookRecordRepository.save(record);
	}
  
}
