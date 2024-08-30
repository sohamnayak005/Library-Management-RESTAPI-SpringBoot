package com.hello.library_restapi_springboot.dto;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import lombok.extern.java.Log;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Data
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   long id;
   String name;
   String email;
   String password;
   String gender;
   long mobile;
   long registerNumber;
   boolean status;
   String token;
   
   @OneToMany
   List<BookRecord> records;
}
