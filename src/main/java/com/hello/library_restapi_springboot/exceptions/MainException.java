package com.hello.library_restapi_springboot.exceptions;

import java.util.InputMismatchException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.hello.library_restapi_springboot.helper.ResponseStructure;


@RestControllerAdvice
public class MainException {
  @ExceptionHandler(ShouldNotFoundException.class)
  public ResponseEntity<ResponseStructure<String>> shouldNotRepeat(ShouldNotFoundException e) {
	  ResponseStructure<String>rs=new ResponseStructure<String>();
	  rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
	  rs.setMessage("There is an Exception");
	  rs.setData(e.getMessage());
	  return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
  }
  
  @ExceptionHandler(InputMismatchException.class)
  public ResponseEntity<ResponseStructure<String>> notFound(InputMismatchException e){
	  ResponseStructure<String> rs=new ResponseStructure<String>();
	  rs.setStatusCode(HttpStatus.NOT_ACCEPTABLE.value());
	  rs.setMessage("There is an Exception");
	  rs.setData(e.getMessage());
	  return new ResponseEntity<ResponseStructure<String>>(rs,HttpStatus.NOT_ACCEPTABLE);
  }
  
  @ExceptionHandler(VerificationPendingException.class)
  public ResponseEntity<ResponseStructure<String>> notVerify(VerificationPendingException e){
	  ResponseStructure<String> rs= new ResponseStructure<>();
	  rs.setData(e.getMessage());
	  rs.setMessage("There is an Exception");
	  rs.setStatusCode(HttpStatus.UNAUTHORIZED.value());
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.NOT_ACCEPTABLE);
  }
}
