package com.app.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

		@ExceptionHandler(NotFoundException.class)
		public ResponseEntity<Map<String,Object>> handleNotFoundExcecption(NotFoundException ex){
			Map<String, Object> errorDetails=new HashMap<String, Object>();
			errorDetails.put("Time Stamp",LocalDateTime.now());
			errorDetails.put("Code", ex.getCode());
			errorDetails.put("Messgae", ex.getMessage());
			errorDetails.put("details","Not Found Exception");
			errorDetails.put("path", "/users"); // You can dynamically get the path if needed
			return new ResponseEntity<Map<String,Object>>(errorDetails, HttpStatus.NOT_FOUND);
		}
		
		@ExceptionHandler(DuplicateExceptions.class)
		public ResponseEntity<Map<String,Object>> handleDuplicateExcecption(DuplicateExceptions ex){
			Map<String, Object> errorDetails=new HashMap<String, Object>();
			errorDetails.put("Time Stamp",LocalDateTime.now());
			errorDetails.put("Code", ex.getCode());
			errorDetails.put("String", ex.getMessage());
			errorDetails.put("details","Duplicate Exception");
			return new ResponseEntity<Map<String,Object>>(errorDetails, HttpStatus.BAD_REQUEST);
		}
	
}
