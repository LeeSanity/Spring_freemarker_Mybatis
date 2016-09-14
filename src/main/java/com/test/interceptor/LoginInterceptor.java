package com.test.interceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private String loginUrl = "/login";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
	  
	    String servletPath = request.getServletPath();
	    String pathInfo = request.getPathInfo();
	    if (pathInfo.length() > 0)
	        servletPath = servletPath + pathInfo;

		if(request.getPathInfo().startsWith(loginUrl))
			return true;
		if (login(request.getCookies()))
			return true;
		System.out.println(request.getContextPath() + loginUrl);
		response.sendRedirect(request.getContextPath() + loginUrl);
		return false;
	}

//	@Override
//	public void postHandle(HttpServletRequest request,
//			HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request,
//			HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//		// TODO Auto-generated method stub
//
//	}
	
	public boolean login(Cookie[] cookies){
		if (cookies == null) 
			return false;
		else{
			for (int i = 0; i < cookies.length; i++) {
				if(cookies[i].getName().equals("userId"))
					return true;
			}
		}
		return false;
	}

}
