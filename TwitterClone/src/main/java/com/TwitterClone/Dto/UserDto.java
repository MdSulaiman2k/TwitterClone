package com.TwitterClone.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserDto {
	
	 @JsonProperty("id")
	 private long id ;
	 
	 @JsonProperty("name")
	 private String name ;
	 
	 @JsonProperty("email")
	 private String email;
	 
	 

	public UserDto(long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}



	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	  
}
