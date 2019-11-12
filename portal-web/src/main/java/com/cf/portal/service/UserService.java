package com.cf.portal.service;

import com.cf.pojo.User;

public interface UserService {
	User getUserByToken(String token);
}
