package com.TwitterClone.Controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Model.Post;
import com.TwitterClone.Service.PostService;
import com.TwitterClone.Validation.PostValidation;
import com.TwitterClone.mapper.PostMapper;

@RestController
@RequestMapping("api/users/{userId}/post")
public class PostController {
	
	@Autowired
	PostService postService ;

	@Autowired
	PostMapper postMapper ;
	
	@GetMapping
	@ResponseBody
	public List<PostDto> allPost(@PathVariable long  userId ) {
		List<Post> posts = postService.findAllPostByUserId(userId) ;
		if(posts == null )
			throw new ResponseStatusException(
				  HttpStatus.NOT_FOUND, "Posts Not Found");
		
		return postMapper.PostToPostDto(posts) ;  	
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public PostDto getPost(@PathVariable long  userId , @PathVariable long  id  ) {	
		Post post = postService.FindPost(id , userId) ;
		if(post == null) 
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Post Not Found");
		
		return postMapper.PostToPostDto(post) ;
	}
	
	@PostMapping
	@ResponseBody
	public PostDto setPost(Post post, @PathVariable long  userId) throws ValidationException {
		post = postService.createPost(post, userId) ;
		return postMapper.PostToPostDto(post) ;
	}
	
	
}
