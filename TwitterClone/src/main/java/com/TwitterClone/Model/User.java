package com.TwitterClone.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public  class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  long id ;
	private String  name ;
	@Column(unique=true, nullable= false)
	private String  email;
	@Size(min = 8 , max = 15,  message = "field size must be between 8 to 15" )
	@Column(nullable = false, length = 16)
	private String password ;
	
	public User(long user_id) {
		id = user_id ;
	}
	
}
