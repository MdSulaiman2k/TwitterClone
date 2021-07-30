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

import com.TwitterClone.Dto.PostReqDto;
import com.TwitterClone.Model.Post;
import com.TwitterClone.Service.PostService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class PostServiceTest {
	
	@Autowired
	PostService postService ;
	
	@Test
	@Order(1)
	public void savePostTest() throws ValidationException {
		PostReqDto postReq = new PostReqDto("Texting post" , "1");
		Post post = postService.createPost(postReq) ;
		assertNotNull(postService.findPost(post.getId()));
	}
	
	@Test
	@Order(2)
	public void readPostTest() throws ValidationException {
		List<Post> postList = postService.findAllPost() ;
		assertThat(postList).size().isGreaterThan(0);	
	}
	

}
