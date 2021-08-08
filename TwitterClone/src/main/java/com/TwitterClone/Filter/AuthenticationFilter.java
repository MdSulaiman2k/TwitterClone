package com.TwitterClone.Filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.TwitterClone.SpringApplicationContext;
import com.TwitterClone.Dto.Request.UserLoginRequestModel;
import com.TwitterClone.Security.SecurityConstants;
import com.TwitterClone.Service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private final AuthenticationManager  authenticationManager ;
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req , HttpServletResponse res){
		try {
			   UserLoginRequestModel crud = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequestModel.class ) ;
			 return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken
					(
					crud.getEmail(),crud.getPassword(),new ArrayList<>()
					)
			   ) ;
			}
			catch(IOException err){
				throw new RuntimeException(err) ;	
			}
	}
	
	@Override
	public  void successfulAuthentication(	HttpServletRequest req, 
											HttpServletResponse res,
											FilterChain chain, 
											Authentication auth) {
		
		String email = ((User) auth.getPrincipal()).getUsername() ;
		String token = Jwts.builder()
			.setSubject(email)
			.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
			.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET)
			.compact() ;
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl") ;
		com.TwitterClone.Model.User user = userService.findEmail(email) ;
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);		
		res.addHeader("userToken", user.getUserToken());
	}
	
	
	
	
	
	

}
