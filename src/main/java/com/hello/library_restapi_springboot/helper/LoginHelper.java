package com.hello.library_restapi_springboot.helper;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginHelper {
	String email;
	String password;
}
