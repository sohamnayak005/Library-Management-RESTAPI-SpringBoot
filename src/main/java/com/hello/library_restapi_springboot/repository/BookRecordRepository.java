package com.hello.library_restapi_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.library_restapi_springboot.dto.BookRecord;

public interface BookRecordRepository extends JpaRepository<BookRecord, Long>{

}
