package com.TwitterClone.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.TwitterClone.Interceptor.UserInterceptor;

@Component
public class UserInterceptorConfig extends WebMvcConfigurationSupport {
	
	@Autowired
	private UserInterceptor userInterceptor;
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(userInterceptor);
	}
}


