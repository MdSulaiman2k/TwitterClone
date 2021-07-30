package com.TwitterClone.Excepton;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustormizeException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String  errorCode ;
	
	private String errorStatus ;
	
	private String errorDescription ;
}
