package com.TwitterClone.Handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.TwitterClone.Excepton.NoSuchElementException;
import com.TwitterClone.Excepton.UnAutherizedException;



@ControllerAdvice
public class ControllerExceptionHandler {
	
	@ExceptionHandler(UnAutherizedException.class)
	public ResponseEntity<String> handleUnauthorizedToken(UnAutherizedException unAutherizedException){
		return new ResponseEntity<String>("Token is UnAutherized", HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<String> handleNoDate(NoSuchElementException noDataException){
		return new ResponseEntity<String>("No Data Found", HttpStatus.NOT_FOUND);
	}
	

}
