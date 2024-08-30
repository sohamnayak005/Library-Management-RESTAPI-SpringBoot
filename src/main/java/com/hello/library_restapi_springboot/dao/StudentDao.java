package com.hello.library_restapi_springboot.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hello.library_restapi_springboot.dto.Student;
import com.hello.library_restapi_springboot.repository.StudentRespository;

@Repository
public class StudentDao {
    @Autowired
    StudentRespository studentRespository;
    
	public Student findByEmail(String email) {
		return studentRespository.findByEmail(email);
	}

	public void save(Student student) {
		studentRespository.save(student);
	}

	public Student findById(long id) {
		return studentRespository.findById(id).orElse(null);
	}

	
	
  
}
