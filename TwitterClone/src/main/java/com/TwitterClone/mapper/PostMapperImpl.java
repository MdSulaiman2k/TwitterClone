package com.TwitterClone.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Model.Post;

@Component
public class PostMapperImpl  implements PostMapper{

	@Override
	public PostDto postToPostDto(Post post) {
		if(post == null)
			return  null ;
		
		PostDto postDto = new PostDto( post.getId() , post.getContent()) ;
		return postDto ;
		
	}

	@Override
	public List<PostDto> postToPostDto(List<Post> posts) {
		if(posts == null)
			return  null ;
		List<PostDto> postsDto = new ArrayList<>() ;
		
		for(Post post : posts) {
			PostDto postDto = new PostDto(post.getId() , post.getContent()) ;
			postsDto.add(postDto) ;
		}
		return postsDto ;
	}

}
