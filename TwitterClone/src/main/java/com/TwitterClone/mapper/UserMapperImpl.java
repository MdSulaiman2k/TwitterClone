package com.TwitterClone.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Model.User;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDto userToUserDto(User user) {
		if(user == null)
			return  null ;
		
		UserDto userdto = new UserDto( user.getName() , user.getEmail()) ;
		return userdto;
	}

	@Override
	public List<UserDto> userToUserDto(List<User> users) {
		if(users == null)
			return  null ;
		List<UserDto> usersDto = new ArrayList<>();
		
		for(User user : users) 
			usersDto.add(userToUserDto(user)) ;
		
		return usersDto ;
		
	}

	@Override
	public User userRequestDtotoUser(UserRequestDto userReq) {
		if(userReq == null)
			return  null ;
		User user = new User() ;
		user.setName(userReq.getName());
		user.setEmail(userReq.getEmail());
		user.setPassword(userReq.getPassword());
		return user ;
	}

}
