package com.TwitterClone.Controller;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Dto.PostReqDto;
import com.TwitterClone.Dto.PostResponseDto;
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
	public List<PostDto> allPost(@RequestParam(value = "page" , defaultValue = "1") int page , 
			@RequestParam(value = "limit" , defaultValue = "3") int limit) {
		List<Post> posts = postService.findAllPost(page , limit) ;
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
	
	@DeleteMapping("/{id}")
	public PostResponseDto deletePost(@PathVariable("id") long id , @RequestHeader("Authorization") String userToken) {
		return postService.deletePost(id , userToken) ; 
	}
	
	@DeleteMapping("/all")
	public ResponseEntity<String>   deleteAllPost(@RequestHeader("Authorization") String userToken) {
		 postService.deleteAllPost(userToken) ;
		 return new ResponseEntity<String>("Deleted " + Thread.currentThread().getName() , HttpStatus.ACCEPTED) ;
	}
	
	
}
