package com.TwitterClone.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
public  class User extends BaseEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private  long id ;
	
	@Column(nullable = false)
	private String  name ;
	
	@Column(unique=true, nullable = false, updatable = false)
	private String userToken ;
	
	@Column(unique=true, nullable= false)
	private String  email;
	
	@Column(nullable = false)
	private String password ;
	
	
	public User(long user_id) {
		id = user_id ;
	}
	
}
