package com.cf.test;


import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cf.pojo.User;
import com.cf.service.UserService;

public class TestMybatis {

	private ApplicationContext ac;
	private UserService userService;
	
	@Before
	public void before() {
		ac = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml"); 
		userService = (UserService)ac.getBean("userServiceImpl");
	}
	
	@Test
	public void test1() {
		User user = userService.selectByPrimaryKey((long)1);
		System.out.println("---------" + user.getUsername());
	}
}
