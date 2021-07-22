package com.TwitterClone;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.xml.bind.ValidationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.TwitterClone.Service.UserService;
import com.TwitterClone.Repository.UserRepository;
import com.TwitterClone.Model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired
	private UserService userService;

	@MockBean
	private UserRepository userRepo;

	@Test
	public void getUsersTest() {
		when(userRepo.findAll()).thenReturn(Stream
				.of(new User(1, "sulaiman", "s32@gmail.com", "sulaiman"), new User(2, "sulaiman", "s31@gmail.com", "sulaiman")).collect(Collectors.toList()));
		assertEquals(2, userService.findAllUsersEmail().size());
	}



	@Test
	public void saveUserTest() throws ValidationException {
		User user = new User(1, "sulaiman", "s32@gmail.com", "sulaiman");
		when(userRepo.save(user)).thenReturn(user);
		assertEquals(user, userService.create(user));
	}

}