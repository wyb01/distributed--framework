package com.cf.sso.controller;

import com.cf.pojo.User;
import com.cf.sso.service.UserService;
import com.cf.utils.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	/**
	* @Description: 校验"用户名"和"手机号"是否被占用
	* @param param:
	* @param type: 1、手机号 2、用户名
	* @Return: com.cf.utils.Result
	* @Author: wyb
	* @Date: 2019-12-25 10:59:37
	*/
	@RequestMapping(value = "user/check/{param}/{type}", method = RequestMethod.GET)
	@ResponseBody
	public Result checkData(@PathVariable("param") String param, @PathVariable("type") Integer type) {
		Result result = userService.checkData(param, type);
		System.out.println("checkdata-------------------" + result);
		return result;
	}

	/**
	* @Description: 用户注册
	* @param user:
	* @Return: com.cf.utils.Result
	* @Author: wyb
	* @Date: 2019-12-25 14:30:46
	*/
	@RequestMapping(value = "user/register", method = RequestMethod.POST)
	@ResponseBody
	public Result createUser(User user) {
		Result result = userService.createUser(user); //创建用户，添加到mysql
		System.out.println("createUser-------------------" + result);
		return result;
	}

	/**
	 * description: 登录
	 * @param username
	 * @param password
	 * @param request
	 * @param response:
	 * @return: com.cf.utils.Result
	 * @author: wyb
	 * @createTime: 2019-12-23 23:20:54
	 */
	@RequestMapping(value = "user/login", method = RequestMethod.POST)
	@ResponseBody
	public Result userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		//登陆成功后，生成token，并添加cookie
		// 然后跳转到portal首页，portal的index页面取出cookie中的token进行再次请求，根据返回的数据获取username并显示
		Result result = userService.userLogin(username, password, request, response);
		System.out.println("userLogin-------------------" + result);
		return result;
	}
	
	/**
	* @Description: 
	* @param token: 登录跳转到首页后，显示用户名等信息
	* @param callback: 
	* @Return java.lang.Object
	* @Author: wyb
	* @Date: 2019-11-14 14:28:58       
	*/
	@RequestMapping(value = "user/token/{token}", method = RequestMethod.GET)
	@ResponseBody
	public Object getUserByToken(@PathVariable("token") String token, String callback) {
		Result result = userService.getUserByToken(token);   //从redis中获取token
		System.out.println("getUserByToken-------------------" + result);
		if (StringUtils.isBlank(callback)) {
			return result;
		} else {
			// jsonp调用
			MappingJacksonValue mappingJackson = new MappingJacksonValue(result);
			mappingJackson.setJsonpFunction(callback);
			return mappingJackson;
		}		
	}

	/**
	* @Description: 退出登录
	* @param token:
	* @param request:
	* @param response:
	* @Return void
	* @Author: wyb
	* @Date: 2019-11-14 14:29:36
	*/
	@RequestMapping(value = "user/logout/{token}", method = RequestMethod.GET)
	@ResponseBody
	public void logout(@PathVariable("token") String token, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Result result = userService.userLogout(token);
		System.out.println("logout-------------------" + result);
		//登出后，跳转到portal首页
		response.sendRedirect("http://localhost:8083/index");
	}
}
