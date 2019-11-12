package com.cf.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cf.portal.service.IndexService;

@Controller
public class IndexController {

	@Autowired
	private IndexService indexService;
	
	@RequestMapping("/index")
	public String shouIndex() {
		return "index";
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public String get(@RequestParam("id") Long id) {
		String result = indexService.get(id);
		System.out.println("----------------portal---------------" + result);
		return result;
	}
	
	@RequestMapping("/my/mypage")
	public String mypage() {
		return "mypage";
	}
}
