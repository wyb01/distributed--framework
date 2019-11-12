package com.cf.service;

import com.cf.pojo.User;

public interface UserService {
	User selectByPrimaryKey(Long id);
}
