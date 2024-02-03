package com.fauzan.DIY_COMMERCE;

import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fauzan.DIY_COMMERCE.exception.ErrorResponse;
import com.fauzan.DIY_COMMERCE.exception.ProductNotFoundException;
import com.fauzan.DIY_COMMERCE.exception.SellerNotFoundException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({SellerNotFoundException.class})
	public ResponseEntity<Object> handleSellerNotFoundException(RuntimeException e) {
		ErrorResponse error = new ErrorResponse(Arrays.asList(e.getMessage()));
		
		return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ProductNotFoundException.class})
	public ResponseEntity<Object> handleProductNotFoundException(RuntimeException e) {
		ErrorResponse error = new ErrorResponse(Arrays.asList(e.getMessage()));
		
		return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
	}
}
