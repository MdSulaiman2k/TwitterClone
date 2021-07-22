package com.TwitterClone.Controller;

import java.util.List;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Model.Post;
import com.TwitterClone.Model.User;
import com.TwitterClone.Service.PostService;
import com.TwitterClone.Service.UserService;
import com.TwitterClone.Validation.UserValidation;
import com.TwitterClone.mapper.PostMapperImpl;
import com.TwitterClone.mapper.UserMapperImpl;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userservice ;
	
	@Autowired
	UserValidation  uservalidate ;
	
	@Autowired
	UserMapperImpl userMapper ;
	
	@Autowired
	PostService postService ;
	
	@Autowired
	PostMapperImpl postMapper ;
	
	
	@PostMapping("/create")
	public ResponseEntity<String> createUser(@Valid  User user){
		try {
			uservalidate.validateUser(user) ;
			userservice.create(user) ;
			return  ResponseEntity.ok(String.valueOf(user.getId())) ;
		}catch(Exception err) {
			return new ResponseEntity(err.getMessage(), HttpStatus.NOT_ACCEPTABLE); 
		}
	}
	
	@GetMapping("/{id}/posts")
	@ResponseBody
	public List<PostDto> allPost(@PathVariable("id") long  userId ) {
		try {
			List<Post> posts;
			posts = postService.findAllPostByUserId(userId);
			if(posts == null )
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Posts Not Found");
		
			return postMapper.PostToPostDto(posts) ;  	
		} catch (ValidationException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "User_id Not Found");
		}
		
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> show(@PathVariable long id) {
		
		if(userservice.findByUserID(id) == null) {
			return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<UserDto>(userMapper.UserToUserDto(userservice.findByUserID(id)), HttpStatus.OK );
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getalluser(){
		try {
		List<User> users = userservice.findAllUsersEmail() ;
		return new ResponseEntity<List<UserDto>>(userMapper.UserToUserDto(users) , HttpStatus.OK) ;
		}catch(Exception err) {
			return  new ResponseEntity<List<UserDto>>(HttpStatus.NOT_ACCEPTABLE);	
		}
	}
	
	
}
