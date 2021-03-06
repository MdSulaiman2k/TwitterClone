package com.TwitterClone.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Model.Post;

@Mapper(componentModel = "spring")
public interface PostMapper {

	PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);
	
	PostDto postToPostDto(Post post) ;

	List<PostDto>   postToPostDto(List<Post> post) ;
	
	
	
}
