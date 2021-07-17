package com.TwitterClone.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Model.User;

@Component
public class UserMapperImpl implements UserMapper {

	@Override
	public UserDto UserToUserDto(User user) {
		if(user == null)
			return  null ;
		
		UserDto userdto = new UserDto(user.getId() ,  user.getName() , user.getEmail()) ;
		return userdto;
	}

	@Override
	public List<UserDto> UserToUserDto(List<User> users) {
		if(users == null)
			return  null ;
		List<UserDto> usersDto = new ArrayList<>();
		
		for(User user : users) 
			usersDto.add(UserToUserDto(user)) ;
		
		return usersDto ;
		
	}

}
