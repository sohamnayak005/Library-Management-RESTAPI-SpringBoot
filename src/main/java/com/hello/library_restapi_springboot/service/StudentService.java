package com.hello.library_restapi_springboot.service;

import org.springframework.http.ResponseEntity;

import com.hello.library_restapi_springboot.dto.Student;
import com.hello.library_restapi_springboot.helper.LoginHelper;
import com.hello.library_restapi_springboot.helper.ResponseStructure;

public interface StudentService{
	ResponseEntity<ResponseStructure<Student>> createStudentAccount(Student student);
	ResponseEntity<ResponseStructure<Student>> createStudentAccount(long id, String token);
	ResponseEntity<ResponseStructure<Student>> login(LoginHelper loginHelper);
	ResponseEntity<ResponseStructure<Student>> borrowBook(long sid, long bid);
}
