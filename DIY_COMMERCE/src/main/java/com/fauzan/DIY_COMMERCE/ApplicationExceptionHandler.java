package com.fauzan.DIY_COMMERCE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fauzan.DIY_COMMERCE.exception.ErrorResponse;
import com.fauzan.DIY_COMMERCE.exception.ProductNotFoundException;
import com.fauzan.DIY_COMMERCE.exception.SellerEmailAlreadyExistException;
import com.fauzan.DIY_COMMERCE.exception.SellerNotFoundException;
import com.fauzan.DIY_COMMERCE.exception.SellerUsernameAlreadyException;
import com.fauzan.DIY_COMMERCE.exception.VariationNotFoundException;

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
	
	@ExceptionHandler({SellerEmailAlreadyExistException.class})
	public ResponseEntity<Object> handleSellerEmailAlreadyExistException(RuntimeException e) {
		ErrorResponse error = new ErrorResponse(Arrays.asList(e.getMessage()));
		
		return new ResponseEntity<Object>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({SellerUsernameAlreadyException.class})
	public ResponseEntity<Object> handleSellerUsernameAlreadyExistException(RuntimeException e) {
		ErrorResponse error = new ErrorResponse(Arrays.asList(e.getMessage()));
		
		return new ResponseEntity<Object>(error,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler({VariationNotFoundException.class})
	public ResponseEntity<Object> handleVariationNotFoundException(RuntimeException e) {
		ErrorResponse error = new ErrorResponse(Arrays.asList(e.getMessage()));
		
		return new ResponseEntity<Object>(error,HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
		
		return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
	}
	
	
}
