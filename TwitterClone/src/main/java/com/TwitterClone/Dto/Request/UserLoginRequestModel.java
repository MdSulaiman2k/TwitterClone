package com.TwitterClone.Dto.Request;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginRequestModel {
	
	private String email ;
	private String password ;

}
