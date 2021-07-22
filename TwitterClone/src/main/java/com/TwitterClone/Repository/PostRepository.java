package com.TwitterClone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.TwitterClone.Model.Post;
import com.TwitterClone.Model.User;

public interface PostRepository extends JpaRepository<Post, Long> {
	  
	List<Post> findByUser(User user) ;
	
	Post findById(long id) ;
	
	@Query("SELECT p FROM Post p WHERE p.user =?2 and p.id =?1")
	Post findByUserPostById(long id , long user_id) ;

}
