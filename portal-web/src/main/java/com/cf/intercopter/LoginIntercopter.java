package com.cf.intercopter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cf.pojo.User;
import com.cf.portal.service.UserService;
import com.cf.utils.CookieUtils;

public class LoginIntercopter implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 判断cookie是否存在
		String token = CookieUtils.getCookieValue(request, "SSO_TOKEN");
		User user = userService.getUserByToken(token);
		if (user == null) {
			response.sendRedirect("http://localhost:8085/sso/login" + "?redirect=" + request.getRequestURL());
			return false;
		}
		System.out.println("-------------------preHandle " + user.getUsername());
		request.setAttribute("user", user);
		return true;
	}

}
