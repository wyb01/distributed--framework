package com.cf.portal.service.impl;

import org.springframework.stereotype.Service;

import com.cf.pojo.User;
import com.cf.portal.service.UserService;
import com.cf.utils.HttpClientUtil;
import com.cf.utils.Result;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User getUserByToken(String token) {
		String json = HttpClientUtil.doGet("http://localhost:8085/user/token/" + token);
		System.out.println("-------------------getUserByToken " + json);
		Result result = Result.formatToPojo(json, User.class);
		if (result.getStatus() == 200) {
			User user = (User)result.getData();
			return user;
		}
		return null;
	}

}
