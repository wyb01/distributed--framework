package com.cf.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.cf.pojo.User;
import com.cf.rest.service.RestService;

@Controller
public class RestController {

	@Autowired
	private RestService restService;
	
	/**
	 * 查询操作 REST风格 : /user/用户id
	 * 
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String get(@PathVariable("id") Long id) {
		User user = restService.selectByPrimaryKey(id);
		String result = JSON.toJSONString(user);
		System.out.println("---------------------------get:" + result);
		
		return result;
	}
	
	/**
	 * 创建操作 REST风格
	 * 
	 */
	@RequestMapping(value = "/user/register", method = RequestMethod.POST, produces = MediaType.TEXT_PLAIN_VALUE + ";charset=utf-8")
	@ResponseBody
	public String register(User user) {

		String result = JSON.toJSONString(user);
		System.out.println("---------------------------Post:" + result);
		
		return result;
	}
	
	/**
	 * 删除操作 REST风格
	 * 
	 */
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@PathVariable("id") Long id) {

		System.out.println("---------------------------delete:" + id);
		
		return "delete";
	}
	
	/**
	 * 更新操作 REST风格
	 * 
	 */
	@RequestMapping(value = "/user/update", method = RequestMethod.PUT)
	@ResponseBody
	public String update(User user) {

		String result = JSON.toJSONString(user);
		System.out.println("---------------------------Put:" + result);
		
		return result;
	}
	
	/**
	 * 传统风格 : /user?name户zhangsan&age=18
	 * 
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public String search(@RequestParam("name") String queryString) {
		
		System.out.println("---------------------------get:" + queryString);
		
		return queryString;
	}
}
