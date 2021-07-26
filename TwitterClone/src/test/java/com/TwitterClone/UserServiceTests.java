package com.TwitterClone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.xml.bind.ValidationException;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.TwitterClone.Model.User;
import com.TwitterClone.Repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTests {


	@Autowired
	private UserRepository userRepo;
	
	@Test
	@Order(1)
	public void saveUserTest() throws ValidationException {
		User user = new User(0, "sulaiman", "s32@gmail.com", "sulaiman");
		userRepo.save(user) ;
		assertNotNull(userRepo.findByEmail("s32@gmail.com"));
	}

	@Test
	@Order(2)
	public void getUsersTest() {
		List<User> listUser = userRepo.findAll() ;
		assertThat(listUser).size().isGreaterThan(0);	
	}

	
}