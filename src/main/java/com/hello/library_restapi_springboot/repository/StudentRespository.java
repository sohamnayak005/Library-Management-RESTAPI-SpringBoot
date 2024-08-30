package com.hello.library_restapi_springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hello.library_restapi_springboot.dto.Student;

public interface StudentRespository extends JpaRepository<Student, Long>{

	Student findByEmail(String email);

}
