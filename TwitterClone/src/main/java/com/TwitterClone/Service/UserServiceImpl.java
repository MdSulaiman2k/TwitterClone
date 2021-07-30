package com.TwitterClone.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitterClone.Dto.PostDto;
import com.TwitterClone.Dto.UserDto;
import com.TwitterClone.Dto.UserTokenDto;
import com.TwitterClone.Dto.Utils;
import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Excepton.CreationException;
import com.TwitterClone.Model.Post;
import com.TwitterClone.Model.User;
import com.TwitterClone.Repository.UserRepository;
import com.TwitterClone.Validation.UserValidation;
import com.TwitterClone.mapper.PostMapper;
import com.TwitterClone.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository usersRepo ;
	
	@Autowired
	Utils createUser_id ;
	
	@Autowired
	UserMapper userMapper ;
	
	@Autowired
	PostService postService ;
	
	@Autowired
	PostMapper postMapper ;
	
	@Autowired
	UserValidation userValidaton ;
	
	@Autowired
	UserTokenDto userTokenDto ;

	@Override
	public List<User> findAllUser() {
		return usersRepo.findAll() ;
	}
	
	@Override
	public UserDto findByUserID(long id){
		return userMapper.userToUserDto(usersRepo.findById(id)) ;
	}
	
	@Override
	public User findByUserToken(String token ) {
		return usersRepo.findByUserToken(token) ;
	}
	

	@Override 
	public User findEmail(String email) {
		return usersRepo.findByEmail(email);
	}
	
	
	@Override
	public List<UserDto> findAllUsersEmail() {
		List<UserDto> userDto = userMapper.userToUserDto(findAllUser()) ;
		return userDto ;
	}
	
	@Override
	public UserTokenDto create(UserRequestDto userReq) {
		User user1 = findEmail(userReq.getEmail()) ;
		if(user1 != null)
			throw new CreationException("406", "Not Acceptable", "email is taken");
		userValidaton.validateUser(userReq) ;
		User user = userMapper.userRequestDtotoUser(userReq) ;
		String userToken = "" ;
		do {
			userToken = createUser_id.generateUserId(10) ;
		}while(usersRepo.findByUserToken(userToken) != null) ;
		user.setUserToken(userToken);
		if(usersRepo.save(user) == null) 
			throw new CreationException("406", "Not Acceptable", "invalid inputs");
		userTokenDto.setUserToken(userToken) ;
		return userTokenDto ;
	}

	@Override
	public List<PostDto> findUserPosts(long userId)  {
		 List<Post> posts = postService.findAllPostByUserId(userId) ;
		 return postMapper.postToPostDto(posts) ;
	}

	@Override
	public UserDto editUser(UserDto userDto , String userId) {
		userId = userId.split(" ")[1] ; 
		User user = findByUserToken(userId) ;
		String email = userDto.getEmail() ;
		User user1 = findEmail(email) ;
		if(user1 != null && user.getId() != user1.getId() )
			throw new CreationException("406", "Not Acceptable", "email is taken");
		userValidaton.validateEmail(email) ;
		userValidaton.validateName(userDto.getName());
		user.setEmail(email);
		user.setName(userDto.getName());
		usersRepo.save(user) ;
		return userDto;
	}
	
}
