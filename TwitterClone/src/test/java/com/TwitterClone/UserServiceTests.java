package com.TwitterClone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.TwitterClone.Model.User;
import com.TwitterClone.Repository.UserRepository;
import com.TwitterClone.Service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTests {


	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserService userService ;
	
	@Test
	@Order(2)
	public void saveUserTest() throws ValidationException {
		User user = new User(0, "sulaiman", "s32@gmail.com", "sulaiman");
		userService.create(user) ;
		assertNotNull(userService.findEmail("s32@gmail.com"));
	}

	@Test
	@Order(3)
	public void getUsersTest() {
		List<User> listUser = userService.findAllUser() ;
		assertThat(listUser).size().isGreaterThan(0);	
	}
	
	@Test
	@Order(1)
	public void DeleteUser() {
		userRepo.deleteByEmail("s32@gmail.com");
		assertThat(userRepo.existsByEmail("s32@gmail.com")).isFalse();
	}

	
}