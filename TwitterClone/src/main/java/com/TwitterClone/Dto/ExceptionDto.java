package com.TwitterClone.Dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {

	private String  errorCode ;
	
	private String errorStatus ;
	
	private String errorDescription ;
}
