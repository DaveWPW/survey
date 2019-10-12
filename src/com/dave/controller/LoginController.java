package com.dave.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dave.common.vo.JsonResult;
/**
 * 登录Controller
 * 
 * @author Dave20191010
 * 
 */
@Controller
@RequestMapping("/")
public class LoginController {
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param isRememberMe
	 * @return
	 */
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username, String password){
		//用户名密码非空判断
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			if(StringUtils.isEmpty(username))return new JsonResult("登录名不能为空");
			if(StringUtils.isEmpty(password))return new JsonResult("密码不能为空");
		}
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject currentUser = SecurityUtils.getSubject();
		try {
			currentUser.login(token);
			return new JsonResult("Login OK!", 1);
		} catch (UnknownAccountException e) {
			return new JsonResult("此用户不存在");
		} catch (IncorrectCredentialsException e) {
			return new JsonResult("密码错误");
		} catch (AuthenticationException e) {
			return new JsonResult("用户名密码错误");
		} catch (RuntimeException e) {
			return new JsonResult("未知错误，请联系管理员");
		}
	}
}