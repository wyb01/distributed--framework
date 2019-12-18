package com.cf.rest.service.impl;

import com.cf.mapper.UserMapper;
import com.cf.pojo.User;
import com.cf.rest.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestServiceImpl implements RestService {

	@Autowired
	private UserMapper userMapper;

//	@Autowired
//	private JedisClient jedisClient;

	@Override
	public User selectByPrimaryKey(Long id) {

		// 从缓存中提取数据
//		try {
//			String result = jedisClient.hget("UserInfo", id + "");
//			if (!StringUtils.isBlank(result)) {
//				User user = JsonUtils.jsonToPojo(result, User.class);
//				return user;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		User user = userMapper.selectByPrimaryKey(id);

//		// 向缓存中添加数据
//		try {
//			String catchStr = JSON.toJSONString(user);
//			jedisClient.hset("UserInfo", id + "", catchStr);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return user;
	}

}
