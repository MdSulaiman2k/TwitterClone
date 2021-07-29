package com.TwitterClone.Controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Dto.PostReqDto;
import com.TwitterClone.Model.Post;
import com.TwitterClone.Service.PostService;
import com.TwitterClone.mapper.PostMapper;

@RestController
@RequestMapping("api/posts")
public class PostController {
	
	@Autowired
	PostService postService ;

	@Autowired
	PostMapper postMapper ;
	
	@GetMapping
	@ResponseBody
	public List<PostDto> allPost(){
		List<Post> posts = postService.findAllPost() ;
		return postMapper.postToPostDto(posts) ;  	
	}
	
	
	@GetMapping("/{id}")
	@ResponseBody
	public PostDto getPost( @PathVariable long  id  ) {	
		try {
		Post post = postService.findPost(id) ;
		if(post == null) 
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, "Post Not Found");
		
		return postMapper.postToPostDto(post) ;
		}catch(Exception err) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, err.getMessage());
		}
	}
	
	@PostMapping
	@ResponseBody
	public PostDto setPost(@RequestBody PostReqDto postReqDto) throws ValidationException {
		try {
		Post post = postService.createPost(postReqDto ) ;
		return postMapper.postToPostDto(post) ;
		}catch(Exception err) {
			throw new ResponseStatusException(
					  HttpStatus.NOT_FOUND, err.getMessage());
		}
		
		
	}
	
	
}
