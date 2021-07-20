package com.TwitterClone.Service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitterClone.Model.User;
import com.TwitterClone.Repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository usersRepo ;

	@Override
	public List<User> findAllUser() {
		return usersRepo.findAll() ;
	}
	
	@Override
	public User findByUserID(long id){
		return usersRepo.findById(id) ;
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
	public User create(User user)  throws ValidationException {
		User user1 = findEmail(user.getEmail()) ;
		if(user1 != null)
			throw new ValidationException("email is taken");
		return usersRepo.save(user) ;
	}
	
	

}
