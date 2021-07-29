package com.TwitterClone.Service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitterClone.Dto.Utils;
import com.TwitterClone.Dto.Request.UserRequestDto;
import com.TwitterClone.Model.User;
import com.TwitterClone.Repository.UserRepository;
import com.TwitterClone.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository usersRepo ;
	
	@Autowired
	Utils createUser_id ;
	
	@Autowired
	UserMapper userMapper ;

	@Override
	public List<User> findAllUser() {
		return usersRepo.findAll() ;
	}
	
	@Override
	public User findByUserID(long id){
		return usersRepo.findById(id) ;
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
	public List<User> findAllUsersEmail() {
		return findAllUser() ;
	}
	
	@Override
	public User create(UserRequestDto userReq)  throws ValidationException {
		User user1 = findEmail(userReq.getEmail()) ;
		if(user1 != null)
			throw new ValidationException("email is taken");
		User user = userMapper.userRequestDtotoUser(userReq) ;
		String userToken = "" ;
		do {
			userToken = createUser_id.generateUserId(10) ;
		}while(usersRepo.findByUserToken(userToken) != null) ;
		user.setUserToken(userToken);
		return usersRepo.save(user) ;
	}
	
	

}
