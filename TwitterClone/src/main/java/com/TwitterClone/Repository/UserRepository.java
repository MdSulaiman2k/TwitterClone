package com.TwitterClone.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.TwitterClone.Model.User;

public interface UserRepository  extends JpaRepository<User,UUID> {
	
}
