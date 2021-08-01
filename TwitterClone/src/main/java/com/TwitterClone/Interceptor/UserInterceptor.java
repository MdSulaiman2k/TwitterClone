package com.TwitterClone.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.TwitterClone.Excepton.UnAutherizedException;
import com.TwitterClone.Service.UserService;

@Component
public class UserInterceptor implements HandlerInterceptor {
	
	@Autowired
	UserService userService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		boolean flag = true;
		String method = request.getMethod();
		if (!method.equals("POST") || !request.getServletPath().equals("/api/users")) {
			String userToken = request.getHeader("Authorization").split(" ")[1] ;
			if(userService.findByUserToken(userToken) == null) {
				throw new UnAutherizedException("401" , "UnAuthorized" , "Invlaid Token");
			}
        }
		return flag ;
	}
	
}

