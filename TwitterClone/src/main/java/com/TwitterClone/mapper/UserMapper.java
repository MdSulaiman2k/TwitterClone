package com.TwitterClone.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	

	UserDto userToUserDto(User user) ;
	
	List<UserDto> userToUserDto(List<User> users) ;
	
	User userRequestDtotoUser(UserRequestDto userReq) ;
}
