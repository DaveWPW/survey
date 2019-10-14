package com.dave.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dave.common.vo.JsonResult;
import com.dave.common.vo.PageObject;
import com.dave.entity.User;
import com.dave.service.UserService;
/**
 * 用户Controller
 * 
 * @author Dave20191011
 * 
 */
@Controller
@RequestMapping("/user/")
public class UserController {
	@Autowired
	private UserService userService;
	/**
	 * 用户管理页面
	 * 
	 * @return system/user_list
	 */
    @RequestMapping("doUserListUI")
	public String doUserListUI(){
		return "system/user_list";
	}
    /**
     * 用户编辑页面
     * 
     * @return system/user_edit
     */
    @RequestMapping("doUserEditUI")
	public String doUserEditUI(){
		return "system/user_edit";
	}
    /**
     * 添加用户
     * 
     * @param user
     * @return
     */
    @RequestMapping("doAddUser")
    @ResponseBody
    public JsonResult doAddUser(User user){
    	if(user == null) {
    		return new JsonResult("保存对象不能为空");
    	}
    	if(StringUtils.isEmpty(user.getUsername())) {
    		return new JsonResult("用户名称不能为空");
    	}
    	if(StringUtils.isEmpty(user.getPassword())) {
    		return new JsonResult("密码不能为空");
    	}
    	if(StringUtils.isEmpty(user.getRealName())) {
    		return new JsonResult("真实名不能为空");
    	}
    	if(StringUtils.isEmpty(user.getStaffId())) {
    		return new JsonResult("员工号不能为空");
    	}
    	if(StringUtils.isEmpty(user.getRoleId())) {
    		return new JsonResult("角色不能为空");
    	}
    	User userData = userService.findUserByUserName(user.getUsername());
    	if(userData != null){
    		return new JsonResult("登录账号已存在");	
    	}
    	int rows = userService.addUser(user);
    	if(rows != 1) {
    		return new JsonResult("添加失败！！");
    	}
    	return new JsonResult("Save OK!", rows);
    }
    /**
     * 查找查询所有用户
     * 
     * @param pageCurrent
     * @return
     */
    @RequestMapping("doFindUserList")
    @ResponseBody
    public JsonResult doFindUserList(Integer pageCurrent, String username, String staffId){
    	if(pageCurrent == null || pageCurrent <= 0) {
    		return new JsonResult("参数不合法");
    	}
    	PageObject<User> pageUser = userService.findUserList(pageCurrent, username, staffId);
    	return new JsonResult(pageUser);
    }
    
    /**
     * 根据用户ID获取用户
     * 
     * @param userId
     * @return
     */
    @RequestMapping("doFindUserById")
	@ResponseBody
    public JsonResult doFindUserById(Integer userId){
    	User user = userService.findUserById(userId);
    	return new JsonResult(user);
    }
    /** 修改更新用户 */
    @RequestMapping("doUpdateUser")
    @ResponseBody
    public JsonResult doUpdateUser(User user){
    	if(user == null) {
    		return new JsonResult("保存对象不能为空");
    	}
    	if(StringUtils.isEmpty(user.getUserId())) {
    		return new JsonResult("用户ID不能为空");
    	}
    	if(StringUtils.isEmpty(user.getUsername())) {
    		return new JsonResult("用户名不能为空");
    	}
    	if(StringUtils.isEmpty(user.getPassword())) {
    		return new JsonResult("密码不能为空");
    	}
    	if(StringUtils.isEmpty(user.getRealName())) {
    		return new JsonResult("真实名不能为空");
    	}
    	if(StringUtils.isEmpty(user.getStaffId())) {
    		return new JsonResult("员工号不能为空");
    	}
    	if(StringUtils.isEmpty(user.getRoleId())) {
    		return new JsonResult("角色ID不能为空");
    	}
    	int rows = userService.updateUser(user);
    	if(rows != 1) {
    		return new JsonResult("修改失败！！");
    	}
    	return new JsonResult("Update OK!", rows);
    }
    
    
    @RequestMapping("doDeleteUser")
    @ResponseBody
    public JsonResult doDeleteUser(Integer userId){
    	if(StringUtils.isEmpty(userId)) {
    		return new JsonResult("用户ID不能为空");
    	}
    	User user = (User)SecurityUtils.getSubject().getPrincipal();
    	if(user.getUserId() == userId) {
    		return new JsonResult("不能删除自身用户");
    	}
    	int rows = userService.deleteUser(userId);
		if(rows != 1)return new JsonResult("删除失败");
		return new JsonResult("Delete OK!", rows);
    }
    
}