package com.TwitterClone.Handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.TwitterClone.Dto.ExceptionDto;
import com.TwitterClone.Excepton.CreationException;
import com.TwitterClone.Excepton.CustormizeException;
import com.TwitterClone.Excepton.NoSuchElementException;
import com.TwitterClone.Excepton.UnAutherizedException;



@ControllerAdvice
public class ControllerExceptionHandler {
	
	@Autowired
	ExceptionDto exception ;
	
	public void custormizeExceptionToExceptionDto(CustormizeException customException) {
		exception.setErrorCode(customException.getErrorCode());
		exception.setErrorDescription(customException.getErrorDescription());
		exception.setErrorStatus(customException.getErrorStatus());
	}
	
	
	@ExceptionHandler(UnAutherizedException.class)
	public ResponseEntity<ExceptionDto> handleUnauthorizedToken(UnAutherizedException unAutherizedException ){
		custormizeExceptionToExceptionDto(unAutherizedException) ;
		return new ResponseEntity<ExceptionDto>(exception , HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ExceptionDto> handleNoDate(NoSuchElementException noDataException ){
		custormizeExceptionToExceptionDto(noDataException) ;
		return new ResponseEntity<ExceptionDto>(exception, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(CreationException.class)
	public ResponseEntity<ExceptionDto> creationException(CreationException creationException ){
		custormizeExceptionToExceptionDto(creationException) ;
		return new ResponseEntity<ExceptionDto>(exception , HttpStatus.NOT_ACCEPTABLE);
	}
	

}
