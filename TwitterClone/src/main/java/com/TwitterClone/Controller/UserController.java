package com.TwitterClone.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Dto.UserTokenDto;
import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Excepton.NoSuchElementException;
import com.TwitterClone.Service.UserService;
import com.TwitterClone.Validation.UserValidation;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userservice ;
	
	@Autowired
	UserValidation  uservalidate ;
			
	
	@PostMapping
	public ResponseEntity<UserTokenDto> createUser(@Valid @RequestBody  UserRequestDto userReq){
			return  new ResponseEntity<UserTokenDto>(userservice.create(userReq), HttpStatus.CREATED); 
	}
	
	@GetMapping("/{id}/posts")
	@ResponseBody
	public List<PostDto> allPost(@PathVariable("id") long   id  ) {
			return userservice.findUserPosts(id) ;  	
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> show(@PathVariable("id") long id) {
		UserDto user =  userservice.findByUserID(id)  ;
		if(user == null) {
			throw new NoSuchElementException("403" , "Forden" , "user_id not  found") ;
		}
		return new ResponseEntity<UserDto>(user, HttpStatus.OK );
	}
	
	@GetMapping
	public ResponseEntity<List<UserDto>> getalluser(@RequestParam(value = "page" , defaultValue = "1") int page , 
			@RequestParam(value = "limit" , defaultValue = "3") int limit ){
		Pageable pageRequest =  PageRequest.of(page, limit) ;
		return new ResponseEntity<List<UserDto>>( userservice.findAllUsersEmail(pageRequest ) , HttpStatus.OK) ;
	}
	
	@PutMapping
	public ResponseEntity<UserDto> editUser(@RequestBody UserDto userDto , @RequestHeader("Authorization") String userToken){
		UserDto userdto = userservice.editUser(userDto, userToken) ;
		return new ResponseEntity<UserDto>(userdto , HttpStatus.OK  ) ;
	}  
	
	
}
