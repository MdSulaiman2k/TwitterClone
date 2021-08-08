package com.TwitterClone.Interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class UserInterceptor implements HandlerInterceptor {
	
//	@Autowired
//	UserService userService;
//	
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		boolean flag = true;
//		System.out.println(SecurityContextHolder.getContext().getAuthentication()) ;
////		String method = request.getMethod();
////		if (!method.equals("POST") || !request.getServletPath().equals("/api/users")) {
////			String userToken = request.getHeader("Authorization").split(" ")[1] ;
////			if(userService.findByUserToken(userToken) == null) {
////				throw new UnAutherizedException("401" , "UnAuthorized" , "Invlaid Token");
////			}
////        }
//		return flag ;
//	}
	
}

