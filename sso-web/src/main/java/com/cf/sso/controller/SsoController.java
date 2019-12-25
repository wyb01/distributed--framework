package com.cf.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sso")
public class SsoController {

	/**
	* @Description: portal-web首页点击"注册"跳转到sso注册页面
	* @Return: java.lang.String
	* @Author: wyb
	* @Date: 2019-12-25 10:45:53
	*/
	@RequestMapping("/register")
	public String register() {
		return "register";
	}

	/**
	* @Description: portal-web首页点击"登录"跳转到sso登录页面
	* @param redirect:
	* @param model:
	* @Return: java.lang.String
	* @Author: wyb
	* @Date: 2019-12-25 10:44:27
	*/
	@RequestMapping("/login")
	public String login(String redirect, Model model) {
		model.addAttribute("redirect", redirect);
		return "login";
	}
}
