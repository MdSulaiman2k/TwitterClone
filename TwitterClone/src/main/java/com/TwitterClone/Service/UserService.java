package com.TwitterClone.Service;

import java.util.Hashtable;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

import com.TwitterClone.Model.User;

@Component
public interface UserService {
	
	List<User> FindAllUser() ;

	User create(User user) throws ValidationException;

	User FindEmail(String email) ;

	List FindAllUsersEmail();

	Hashtable<String, String> FindByUserID(long id);

}
