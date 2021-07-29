package com.TwitterClone.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TwitterClone.Model.User;

@Repository
public interface UserRepository  extends JpaRepository<User, Long > {
	User findByEmail(String email);
	
	User findById(long id) ;
	
	@Transactional
	void  deleteByEmail(String email);
	
	boolean existsByEmail(String email) ;
	
	User findByUserToken(String userToken) ;
}
