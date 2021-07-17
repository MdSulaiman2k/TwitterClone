package com.TwitterClone.Service;

import java.util.Hashtable;
import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

import com.TwitterClone.Model.User;

@Component
public interface UserService {
	
	List<User> findAllUser() ;

	User create(User user) throws ValidationException;

	User findEmail(String email) ;

	List findAllUsersEmail();

	User findByUserID(long id);

}
