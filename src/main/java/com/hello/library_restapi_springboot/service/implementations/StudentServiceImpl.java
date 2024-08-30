package com.hello.library_restapi_springboot.service.implementations;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hello.library_restapi_springboot.dao.BookDao;
import com.hello.library_restapi_springboot.dao.BookRecordDao;
import com.hello.library_restapi_springboot.dao.StudentDao;
import com.hello.library_restapi_springboot.dto.Book;
import com.hello.library_restapi_springboot.dto.BookRecord;
import com.hello.library_restapi_springboot.dto.Student;
import com.hello.library_restapi_springboot.exceptions.ShouldNotFoundException;
import com.hello.library_restapi_springboot.exceptions.VerificationPendingException;
import com.hello.library_restapi_springboot.helper.EmailSender;
import com.hello.library_restapi_springboot.helper.LoginHelper;
import com.hello.library_restapi_springboot.helper.ResponseStructure;
import com.hello.library_restapi_springboot.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    
    @Autowired
    BookDao bookDao;
    
    @Autowired
    BookRecordDao bookRecordDao;
    
    @Autowired
    EmailSender emailSender;
    
	@Override
	public ResponseEntity<ResponseStructure<Student>> createStudentAccount(Student student) {
		if(studentDao.findByEmail(student.getEmail())==null) {
			String token="stu"+new Random().nextInt(100000,1000000);
			student.setToken(token);
			studentDao.save(student);
			emailSender.studentSignup(student);
			ResponseStructure<Student>rs=new ResponseStructure<Student>();
			rs.setStatusCode(HttpStatus.PROCESSING.value());
			rs.setMessage("Verification Link Sent Successfully");
			rs.setData(student);
			return ResponseEntity.ok(rs);
		}
		else {
			throw new ShouldNotFoundException("Email Should be Unique");
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Student>> createStudentAccount(long id, String token) {
		Student student=studentDao.findById(id);
		if(student.getToken().equals(token)) {
			student.setStatus(true);
			studentDao.save(student);
			ResponseStructure<Student>rs=new ResponseStructure<Student>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setData(student);
			rs.setMessage("Account Created Success");
			return new ResponseEntity<ResponseStructure<Student>>(rs,HttpStatus.CREATED);
		}
		else {
			throw new InputMismatchException("Invalid Token");
		}
		
	}

	@Override
	public ResponseEntity<ResponseStructure<Student>> login(LoginHelper loginHelper) {
		System.out.println(loginHelper.getEmail());
		Student student=studentDao.findByEmail(loginHelper.getEmail());
		if(student==null) {
			throw new InputMismatchException("Invalid Email");
		}
		else {
			if(student.getPassword().equals(loginHelper.getPassword())) {
				if(student.isStatus()) {
					ResponseStructure<Student> structure = new ResponseStructure<Student>();
					structure.setData(student);
					structure.setMessage("Login Success");
					structure.setStatusCode(HttpStatus.FOUND.value());
					return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.FOUND);
				}
				else {
					throw new VerificationPendingException("OTP Verification Pending");
				}
			}
			else {
				throw new InputMismatchException("Invalid Password");
			}
		}
	}

	@Override
	public ResponseEntity<ResponseStructure<Student>> borrowBook(long sid, long bid) {
		Student student=studentDao.findById(sid);
		Book book=bookDao.findById(bid);
		boolean flag=true;
		
		List<BookRecord> bookRecords=student.getRecords();
		if(!bookRecords.isEmpty()) {
			for(BookRecord bookRecord:bookRecords) {
				if(bookRecord.getBook().getId()==bid) {
					flag=false;
					break;
				}
			}
		}
		
		if(book.isStatus() && flag) {
			book.setQuantity(book.getQuantity()-1);
			if(book.getQuantity()<1)
				book.setStatus(false);
			BookRecord record=new BookRecord();
			record.setBook(book);
			record.setStudent(student);
			record.setIssueDate(LocalDate.now());
			bookRecordDao.add(record);
		}
		
		return null;
	}

}
