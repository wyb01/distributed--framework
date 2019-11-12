package com.cf.sso.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cf.pojo.User;
import com.cf.utils.Result;

public interface UserService {
	Result checkData(String context, Integer type);
	Result createUser(User user);
	Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response);
	Result getUserByToken(String token);
	Result userLogout(String token);
}
