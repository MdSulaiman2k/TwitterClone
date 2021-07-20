package com.TwitterClone.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PostDto {
	
	@JsonProperty("id")
	private long id ;
	
	@JsonProperty("Post")
	private String description ;
	
	public PostDto() {
		super() ;
	}

	public PostDto(long id, String description) {
		super();
		this.id = id;
		this.description = description;
	}
	
	
}
