package com.hello.library_restapi_springboot.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.hello.library_restapi_springboot.dto.Student;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailSender {
  @Autowired
  JavaMailSender mailSender;
  
  public void studentSignup(Student student) {
	  MimeMessage mimeMessage = mailSender.createMimeMessage();
	  MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

		try {
			helper.setFrom("sohamnayak005@gmail.com");
			helper.setSubject("Verification Link");
			helper.setTo(student.getEmail());
			String gender = "";
			if (student.getGender().equals("male"))
				gender = "Mr. ";
			else
				gender = "Ms. ";

			String message = "<h1 style='color:#500050'>Hello " + gender + student.getName()
					+ ",<br>Your verification link to Creating Account with us is<h1><h3>Your Token is :"
					+ "<span style='background-color:yellow'>"+student.getToken()+"</span></h3>";

			helper.setText(message, true);
			
			mailSender.send(mimeMessage);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}

  }
}
