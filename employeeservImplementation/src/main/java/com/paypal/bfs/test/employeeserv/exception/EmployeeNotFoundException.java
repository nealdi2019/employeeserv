package com.paypal.bfs.test.employeeserv.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User Not found")
public class EmployeeNotFoundException extends RuntimeException {
}
