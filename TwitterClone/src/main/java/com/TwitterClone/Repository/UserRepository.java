package com.TwitterClone.Repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.TwitterClone.Model.User;

public interface UserRepository  extends CrudRepository<User,UUID> {
	
}
