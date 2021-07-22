package com.TwitterClone.Validation;

import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

import com.TwitterClone.Model.User;

@Component
public class UserValidation {
	
	public void validateEmail(String email) throws ValidationException {
		if(email == null) 
			throw new ValidationException("email is null");
		else if( !email.contains("@"))
			throw new ValidationException("email must contain @sign");
		
	}
	
	public void validateName(String name) throws ValidationException{
		if(name == null) 
			throw new ValidationException("name is null");
		else if(name.length() <= 1) 
			throw new ValidationException("name should be more than one");
	}
	
	public void validatePassword(String password) throws ValidationException{
		if(password.length() < 8 || password.length() > 16  ) 
			throw new ValidationException("password  8 to 16 characters only allowed") ;
		
	}
	
	public void validateUser(User user) throws ValidationException{
		validateName(user.getName()) ;
		validateEmail(user.getEmail());
		validatePassword(user.getPassword()) ;
	}
}
