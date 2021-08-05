package com.TwitterClone.Service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Dto.UserTokenDto;
import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Model.User;

@Component
public interface UserService {
	
	List<User> findAllUser(int page , int limit) ;

	UserTokenDto create(UserRequestDto userReq) ;

	User findEmail(String email) ;

	List<UserDto> findAllUsersEmail(int page , int limit);

	UserDto findByUserID(long id);

	User findByUserToken(String token);
	
	List<PostDto> findUserPosts(long id) ;
	
	UserDto editUser(UserDto userDto , String user_id) ;

}
