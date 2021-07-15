package com.TwitterClone.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.TwitterClone.Repository.UserRepository;
import com.TwitterClone.Model.User;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserRepository userRepo  ;
	
	public ResponseEntity<User> create(){
		User user = userRepo.findById(1) ;
		return  new ResponseEntity<User>(user , HttpStatus.OK);
	}

}
