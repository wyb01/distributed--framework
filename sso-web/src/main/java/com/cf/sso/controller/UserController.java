package com.cf.sso.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.pojo.User;
import com.cf.sso.service.UserService;
import com.cf.utils.Result;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "user/check/{param}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public Result checkData(@PathVariable("param") String param, @PathVariable("type") Integer type) {
		Result result = userService.checkData(param, type);
		System.out.println("checkdata-------------------" + result);
		return result;
	}
	
	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	@ResponseBody
	public Result createUser(User user) {
		Result result = userService.createUser(user);
		System.out.println("createUser-------------------" + result);
		return result;
	}
	
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	@ResponseBody
	public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		Result result = userService.userLogin(username, password, request, response);
		System.out.println("userLogin-------------------" + result);
		return result;
	}
	
	@RequestMapping(value = "user/token/{token}", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable("token") String token, String callback) {
		Result result = userService.getUserByToken(token);
		System.out.println("getUserByToken-------------------" + result);
		
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			// jsonp调用
			MappingJacksonValue mappingJackson = new MappingJacksonValue(result);
			mappingJackson.setJsonpFunction(callback);
			return mappingJackson;
		}		
	}
	
	@RequestMapping(value = "user/logout/{token}", method = RequestMethod.GET)
	@ResponseBody
	public void logout(@PathVariable("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Result result = userService.userLogout(token);
		System.out.println("logout-------------------" + result);
		
		response.sendRedirect("http://localhost:8083/index");
	}
}
