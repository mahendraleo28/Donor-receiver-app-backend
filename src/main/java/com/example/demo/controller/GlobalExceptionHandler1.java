//package com.example.demo.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler1 {
//
//	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler1.class);
//
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<String> handleException(Exception e) {
//		logger.error("An error occurred: {}", e.getMessage(), e);
//		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//				.body("An error occurred. Please see the console.");
//	}
//}
