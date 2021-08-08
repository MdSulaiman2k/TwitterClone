package com.TwitterClone.Filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.TwitterClone.Security.SecurityConstants;

import io.jsonwebtoken.Jwts;

public class AutherizationFilter extends BasicAuthenticationFilter {

	public AutherizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest req, 
			HttpServletResponse res, 
			FilterChain filter) throws IOException, ServletException {
		String header = req.getHeader(SecurityConstants.HEADER_STRING) ;
		
		if(header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) 
			{
				SecurityContextHolder.getContext().setAuthentication(null);
				filter.doFilter(req, res);
				return ;
			}
		header = header.replace(SecurityConstants.TOKEN_PREFIX, "") ;
		try {
		UsernamePasswordAuthenticationToken authentication = getAuthentication(header) ;
		SecurityContextHolder.getContext().setAuthentication(authentication);
		System.out.println(SecurityContextHolder.getContext().getAuthentication()) ;
		filter.doFilter(req, res);
		}catch(Exception err) {
			SecurityContextHolder.getContext().setAuthentication(null);
			filter.doFilter(req, res);
			return ;
		}
		
	}
	
	private UsernamePasswordAuthenticationToken getAuthentication(String token) throws Exception {
		String user = Jwts.parser()
				      .setSigningKey(SecurityConstants.TOKEN_SECRET)
				      .parseClaimsJws(token)
				      .getBody()
				      .getSubject() ;
		if(user != null) {
			return new UsernamePasswordAuthenticationToken(user,null , new ArrayList<>()) ;
		}
		return null ;
	}

}
