package com.cf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.pojo.User;
import com.cf.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/user/{id}")
	@ResponseBody
	public User getUserById(@PathVariable Long id) {
		User user = userService.selectByPrimaryKey(id);
		return user;
	}
}
