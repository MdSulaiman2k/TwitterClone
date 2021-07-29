package com.TwitterClone.Service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.stereotype.Component;

import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Model.User;

@Component
public interface UserService {
	
	List<User> findAllUser() ;

	User create(UserRequestDto userReq) throws ValidationException;

	User findEmail(String email) ;

	List<User> findAllUsersEmail();

	User findByUserID(long id);

	User findByUserToken(String token);

}
