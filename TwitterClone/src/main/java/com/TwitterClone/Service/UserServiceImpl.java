package com.TwitterClone.Service;

import java.util.ArrayList;
import java.util.Hashtable;
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
	public List<User> FindAllUser() {
		return usersRepo.findAll() ;
	}
	
	@Override
	public Hashtable<String, String>  FindByUserID(long id) {
		User user =  usersRepo.findById(id) ;
		return getUserHash(user) ;
	}
	

	@Override 
	public User FindEmail(String email) {
		return usersRepo.findByEmail(email);
	}
	
	public Hashtable<String, String> getUserHash(User user){
		Hashtable<String, String> hash = new Hashtable<String, String>();
		 hash.put("id", String.valueOf(user.getId())) ;
		 hash.put("email", String.valueOf(user.getEmail())) ;
		 return hash;
	}
	
	public List getUsersHash(List<User> users) {
		List arrayList = new ArrayList() ;
		for(User user : users ) {
			 Hashtable<String, String> hash = getUserHash(user) ;
			 arrayList.add(hash) ;
		}
		return arrayList;
	}
	
	
	
	@Override
	public List FindAllUsersEmail() {
		List<User> users = FindAllUser() ;
		return getUsersHash(users) ;
	}
	
	@Override
	public User create(User user)  throws ValidationException {
		User user1 = FindEmail(user.getEmail()) ;
		if(user1 != null)
			throw new ValidationException("email is taken");
		return usersRepo.save(user) ;
	}
	
	

}
