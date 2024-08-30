package com.hello.library_restapi_springboot.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShouldNotFoundException extends RuntimeException{
  String message="This Field Should Not be Repeated";
}
