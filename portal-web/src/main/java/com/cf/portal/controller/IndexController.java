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

	/**
	* @Description: 通过HttpClient访问restful-web(8082)服务获取数据
	* @param id:
	* @Return java.lang.String
	* @Author: wyb
	* @Date: 2019-11-13 11:51:57
	 * 访问示例：http://localhost:8083/get?id=1
	*/
	@RequestMapping("/get")
	@ResponseBody
	public String get(@RequestParam("id") Long id) {
		String result = indexService.get(id);
		System.out.println("----------------portal---------------" + result);
		return result;
	}
	
	/**
	* @Description: 测试拦截
	* @Return: java.lang.String
	* @Author: wyb  
	* @Date: 2019-12-25 16:08:17       
	*/
	@RequestMapping("/my/mypage")
	public String mypage() {
		return "mypage";
	}
}
