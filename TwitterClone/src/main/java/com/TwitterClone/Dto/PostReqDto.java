package com.TwitterClone.Dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReqDto {

	private long id ;
	
	private String  content ;
	
	private long user_id ;
}
