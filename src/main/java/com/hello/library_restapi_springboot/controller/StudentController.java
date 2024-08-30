package com.hello.library_restapi_springboot.controller;

import java.security.PublicKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hello.library_restapi_springboot.dto.Student;
import com.hello.library_restapi_springboot.helper.LoginHelper;
import com.hello.library_restapi_springboot.helper.ResponseStructure;
import com.hello.library_restapi_springboot.service.StudentService;

@RestController
@RequestMapping("api/v1")

public class StudentController {
    @Autowired
    StudentService studentService;
    
    
	@PostMapping("/students")
	public ResponseEntity<ResponseStructure<Student>> createStudent(@RequestBody Student student) {
		return studentService.createStudentAccount(student);
	}
	
	@GetMapping("students/verify/{id}/{token}")
	public ResponseEntity<ResponseStructure<Student>> createStudent(@PathVariable long id,@PathVariable String token) {
		return studentService.createStudentAccount(id,token);
	}
	
	@PostMapping("students/login")
	public ResponseEntity<ResponseStructure<Student>>loginStudent(@RequestBody LoginHelper loginHelper){
		return studentService.login(loginHelper);
	}
	
	@PostMapping("/students/book/{sid}/{bid}")
	public ResponseEntity<ResponseStructure<Student>>borrowBook(@RequestParam long sid,@RequestParam long bid){
		return studentService.borrowBook(sid,bid);
	}
}
