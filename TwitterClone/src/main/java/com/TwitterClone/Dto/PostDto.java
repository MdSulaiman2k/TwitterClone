package com.TwitterClone.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	@JsonProperty("id")
	private long id ;
	
	@JsonProperty("user_id")
	private long user_id ;
	
	@JsonProperty("content")
	private String content ;
		
}
