package com.TwitterClone.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	
		
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	UserDto UserToUserDto(User user) ;
	
	List<UserDto> UserToUserDto(List<User> users) ;
}
