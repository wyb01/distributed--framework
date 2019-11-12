package com.cf.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cf.mapper.UserMapper;
import com.cf.pojo.User;
import com.cf.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User selectByPrimaryKey(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

}
