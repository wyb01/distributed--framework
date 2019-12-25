package com.cf.sso.service.impl;

import com.cf.mapper.UserMapper;
import com.cf.pojo.User;
import com.cf.sso.dao.JedisClient;
import com.cf.sso.service.UserService;
import com.cf.utils.CookieUtils;
import com.cf.utils.JsonUtils;
import com.cf.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private JedisClient jedisClient;

	/**
	* @Description: 校验"用户名"和"手机号"是否被占用
	* @param context:
	* @param type: 1:手机号 2"用户名
	* @Return: com.cf.utils.Result
	* @Author: wyb
	* @Date: 2019-12-25 10:48:31
	*/
	@Override
	public Result checkData(String context, Integer type) {
		//构造查询条件
		User user = new User();
		if (type == 1) {
			user.setUsername(context);
		} else if (type == 2) {
			user.setPhone(context);
		}
		List<User> result = userMapper.selectByCondition(user);
		if (result == null || result.size() == 0) {
			return Result.ok(true);
		}
		return Result.ok(false);
	}

	/**
	 * description: 创建用户
	 * @param user:
	 * @return: com.cf.utils.Result
	 * @author: wyb
	 * @createTime: 2019-12-23 22:47:11
	 */
	@Override
	public Result createUser(User user) {
		user.setCreated(new Date());
		user.setUpdated(new Date());
		user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		userMapper.insert(user);
		return Result.ok();
	}

	/**
	 * @Description: 用户登录
	 * @param token:
	 * @Return com.cf.utils.Result
	 * @Author: wyb
	 * @Date: 2019-11-14 14:18:05
	 */
	@Override
	public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setUsername(username);
		List<User> list = userMapper.selectByCondition(user); //数据库查询用户是否存在
		if (list == null || list.size() == 0) {
			return Result.build(400, "用户名或密码错误");
		}
		User result = list.get(0);
		if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(result.getPassword())) {
			return Result.build(400, "用户名或密码错误");
		}
		// 验证通过的场合
		String token = UUID.randomUUID().toString();  //生成token
		result.setPassword(null);  					  //密码不保存到redis
		jedisClient.set("USER_SESSION_KEY" + ":" + token, JsonUtils.objectToJson(result)); //用户信息存入redis
		jedisClient.expire("USER_SESSION_KEY" + ":" + token, 900);   //过期时间
		
		// 添加写cookie的逻辑，cookie的有效期是关闭浏览器就失效
		CookieUtils.setCookie(request, response, "SSO_TOKEN", token);
		return Result.ok(token);
	}

	/**
	 * description: 根据token获取用户
	 * @param token:
	 * @return: com.cf.utils.Result
	 * @author: wyb
	 * @createTime: 2019-12-23 22:46:47
	 */
	@Override
	public Result getUserByToken(String token) {
		String json = jedisClient.get("USER_SESSION_KEY" + ":" + token); //redis查询key
		if (StringUtils.isBlank(json)) {
			return Result.build(400, "此session已经过期，请重新登录");
		}
		jedisClient.expire("USER_SESSION_KEY" + ":" + token, 900); //重新设置过期时间
		return Result.ok(JsonUtils.jsonToPojo(json, User.class));
	}

	/**
	 * description: 退出登录
	 * @param token: token
	 * @return: com.cf.utils.Result
	 * @author: wyb
	 * @createTime: 2019-12-23 22:46:31
	 */
	@Override
	public Result userLogout(String token) {
		long delCnt = jedisClient.del("USER_SESSION_KEY" + ":" + token);  //删除key
		if (delCnt == 0) {
			return Result.build(400, "此session无效，无法登出");
		}
		return Result.ok();
	}

}
