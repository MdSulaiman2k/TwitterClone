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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Excepton.NoSuchElementException;
import com.TwitterClone.Excepton.UnAutherizedException;
import com.TwitterClone.Model.Post;
import com.TwitterClone.Model.User;
import com.TwitterClone.Service.PostService;
import com.TwitterClone.Service.UserService;
import com.TwitterClone.Validation.UserValidation;
import com.TwitterClone.mapper.PostMapperImpl;
import com.TwitterClone.mapper.UserMapper;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userservice ;
	
	@Autowired
	UserValidation  uservalidate ;
	
	@Autowired
	UserMapper userMapper ;
	
	@Autowired
	PostService postService ;
	
	@Autowired
	PostMapperImpl postMapper ;
	
	
	@PostMapping
	public ResponseEntity<String> createUser(@Valid @RequestBody  UserRequestDto userReq){
		try {
			uservalidate.validateUser(userReq) ;
			User user = userservice.create(userReq) ;
			return  new ResponseEntity<String>(user.getUserToken(), HttpStatus.CREATED); 
		}catch(Exception err) {
			return new ResponseEntity<String>(err.getMessage(), HttpStatus.NOT_ACCEPTABLE); 
		}
	}
	
	@GetMapping("/{user_token}/posts")
	@ResponseBody
	public List<PostDto> allPost(@PathVariable("user_token") String   userId , @RequestHeader("Authorization") String userToken ) {
		try {
			if(!userToken.split(" ")[1].equals(userId) )
				throw new UnAutherizedException();
			List<Post> posts;
			User user = userservice.findByUserToken(userId) ;
			posts = postService.findAllPostByUserId(user.getId());
			if(posts == null )
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "Posts Not Found");
		
			return postMapper.postToPostDto(posts) ;  	
		} catch (ValidationException e) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND, "User_id Not Found");
		}
		
	}

	
	@GetMapping("/{user_id}")
	public ResponseEntity<UserDto> show(@PathVariable("user_id") String userId) {
		if(userservice.findByUserToken(userId) == null) {
			throw new NoSuchElementException() ;
		}

		return new ResponseEntity<UserDto>(userMapper.userToUserDto(userservice.findByUserToken(userId)), HttpStatus.OK );
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getalluser(){
		try {
		List<User> users = userservice.findAllUsersEmail() ;
		return new ResponseEntity<List<UserDto>>(userMapper.userToUserDto(users) , HttpStatus.OK) ;
		}catch(Exception err) {
			return  new ResponseEntity<List<UserDto>>(HttpStatus.NOT_ACCEPTABLE);	
		}
	}
	
	
}
