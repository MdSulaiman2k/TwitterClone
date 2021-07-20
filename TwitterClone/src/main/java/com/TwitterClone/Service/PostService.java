package com.TwitterClone.Service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitterClone.Model.Post;
import com.TwitterClone.Model.User;
import com.TwitterClone.Repository.PostRepository;
import com.TwitterClone.Repository.UserRepository;
import com.TwitterClone.Validation.PostValidation;

@Service
public class PostService {
	
	@Autowired
	PostRepository postRepo ;
	
	@Autowired
	PostValidation postValidation ;
	
	@Autowired 
	UserRepository userRepo;
	
	public List<Post>  findAllPostByUserId(long userId ){
		return postRepo.findByUserId(userId) ;
	}
	
	public Post FindPost(long id , long userId) {
		return postRepo.findById(id);
	}
	
	public Post createPost(Post post , long user_id)  throws ValidationException {
		if(userRepo.findById(user_id) != null) {
			post.setUser(new User(user_id));
			postRepo.save(post);
			return post ;
		}
		else {
			throw new ValidationException("User_id not found");
		}
	} 

}
