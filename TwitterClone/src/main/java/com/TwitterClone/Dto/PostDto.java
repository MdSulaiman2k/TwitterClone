package com.TwitterClone.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
	@JsonProperty("ID")
	private long Id ;
	
	@JsonProperty("content")
	private String content ;
		
}
