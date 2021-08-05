package com.TwitterClone.Service;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.TwitterClone.Dto.PostReqDto;
import com.TwitterClone.Dto.PostResponseDto;
import com.TwitterClone.Excepton.NoSuchElementException;
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
	
	public List<Post>  findAllPostByUserId(long userId ) {
		User user = userRepo.findById(userId) ;
		if(user == null)
			throw new NoSuchElementException("403" , "Forbiden" , "User_id not Found");
		return postRepo.findByUser(user) ;
	}
	
	public List<Post> findAllPost(int page , int limit ){
		page =  page > 0 ? --page : 0 ;
		Pageable pageRequest =  PageRequest.of(page, limit) ;
		return postRepo.findAll(pageRequest).getContent() ;
	}
	
	public Post findPost(long id) {
		return postRepo.findById(id);
	}
	
	public Post createPost(PostReqDto postReqDto)  throws ValidationException {
		User user = userRepo.findById(postReqDto.getUserId()) ;
		if( user != null) {
			Post post = new Post( postReqDto.getContent() ,new User(user.getId())  );
			post = postRepo.save(post);
			return post ;
		}
		else {
			throw new ValidationException("User_id not found");
		}
	}
	
	public PostResponseDto deletePost(long id , String userToken) {
		userToken = userToken.split(" ")[1] ; 
		User user = userRepo.findByUserToken(userToken);
		Post post = postRepo.findByUserPostById(id, user) ;
		if(post == null) {
			throw new NoSuchElementException("403" , "Forbiden" , "Post not Found") ;
		}
		postRepo.deleteById(post.getId());  
		PostResponseDto postResponseDto = new PostResponseDto(post.getId()) ;
		return postResponseDto ;
	}
	
	@Async
	public void deleteAllPost(String userToken){
		userToken = userToken.split(" ")[1] ; 
		User user = userRepo.findByUserToken(userToken);
		postRepo.deleteallByUserId(user.getId()) ;
	}
	

}
