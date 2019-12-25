package com.cf.intercopter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cf.pojo.User;
import com.cf.portal.service.UserService;
import com.cf.utils.CookieUtils;

/**
* @Description: 自定义拦截器
* @Author: wyb
* @Date: 2019-12-25 10:07:57
*/
public class LoginIntercopter implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// 判断cookie是否存在
		String token = CookieUtils.getCookieValue(request, "SSO_TOKEN");
		User user = userService.getUserByToken(token);  //sso-web中查询获取用户
		if (user == null) {
			response.sendRedirect("http://localhost:8085/sso/login" + "?redirect=" + request.getRequestURL());
			return false;
		}
		System.out.println("-------------------preHandle " + user.getUsername());
		request.setAttribute("user", user);
		return true;
	}

}
