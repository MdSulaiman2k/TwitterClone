package com.TwitterClone.Dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonSetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostReqDto {
	
	private String  content ;
	
	private String userId ;
}
