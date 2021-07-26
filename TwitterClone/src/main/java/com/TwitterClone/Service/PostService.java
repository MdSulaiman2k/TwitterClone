package com.TwitterClone.Service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TwitterClone.Dto.PostReqDto;
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
	
	public List<Post>  findAllPostByUserId(long userId ) throws ValidationException {
		User user = userRepo.findById(userId) ;
		if(user == null)
			throw new ValidationException("User_id not found");
		return postRepo.findByUser(user) ;
	}
	
	public List<Post> findAllPost(){
		return postRepo.findAll() ;
	}
	
	public Post findPost(long id) {
		return postRepo.findById(id);
	}
	
	public Post createPost(PostReqDto postReqDto)  throws ValidationException {
		if(userRepo.findById(postReqDto.getUserId()) != null) {
			Post post = new Post( postReqDto.getContent() ,new User(postReqDto.getUserId())  );
			post = postRepo.save(post);
			return post ;
		}
		else {
			throw new ValidationException("User_id not found");
		}
	} 

}
