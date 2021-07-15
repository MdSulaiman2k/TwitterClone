package com.TwitterClone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TwitterClone.Model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long > {
	
	public User findById(long id) ;
}
