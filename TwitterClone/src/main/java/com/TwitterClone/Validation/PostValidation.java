package com.TwitterClone.Validation;

import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

import com.TwitterClone.Model.Post;

@Component
public class PostValidation {
	
	public void validateDescription(String desc)throws ValidationException {
		if(desc == null) 
			throw new ValidationException("Content is null") ;
	}
	
	public void validatePost(Post post) throws ValidationException {
		validateDescription(post.getContent()) ;
	}

}
