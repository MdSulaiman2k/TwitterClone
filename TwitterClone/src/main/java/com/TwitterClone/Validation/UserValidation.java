package com.TwitterClone.Validation;

import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Excepton.CreationException;

@Component
public class UserValidation {
	
	public void validateEmail(String email) {
		if(email == null) 
			throw new CreationException("406", "Not Acceptable", "email is null");
		else if( !email.contains("@"))
			throw new CreationException("406", "Not Acceptable","email must contain @sign");
		
	}
	
	public void validateName(String name) {
		if(name == null) 
			throw new CreationException("406", "Not Acceptable","name is null");
		else if(name.length() <= 1) 
			throw new CreationException("406", "Not Acceptable","name should be more than one");
	}
	
	public void validatePassword(String password){
		if(password.length() < 8 || password.length() > 16  ) 
			throw new CreationException("406", "Not Acceptable","password  8 to 16 characters only allowed") ;
		
	}
	
	public void validateUser(UserRequestDto user){
		validateName(user.getName()) ;
		validateEmail(user.getEmail());
		validatePassword(user.getPassword()) ;
	}
}
