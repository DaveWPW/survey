package com.dave.common.util;

import org.apache.shiro.SecurityUtils;

import com.dave.entity.User;

public class ShiroUtil {
	
	public static User getCurrentUser() {
		return (User)SecurityUtils.getSubject().getPrincipal();
	}
	
}
