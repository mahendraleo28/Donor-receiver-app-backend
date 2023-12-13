//package com.example.demo.globalexception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleException(Exception e) {
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Input Fields cannot be Empty");
//	}
//
//	@ExceptionHandler(EmptyFieldException.class)
//	public ResponseEntity<String> handleEmptyFieldException(EmptyFieldException e) {
//		return ResponseEntity.badRequest().body(e.getMessage());
//	}
//}
