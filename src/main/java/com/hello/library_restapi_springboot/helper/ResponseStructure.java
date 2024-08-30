package com.hello.library_restapi_springboot.helper;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseStructure<T> {
int statusCode;
  String message;
  T data;
}
