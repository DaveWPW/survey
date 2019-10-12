package com.dave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dave.entity.User;

/**
 * User持久层映射接口
 * 
 * @author Dave20191011
 * 
 */
public interface UserDao {
	
	@Select("select * from su_user where status = 1 and username = #{username}")
	User findUserByUserName(@Param("username")String username);
	
	@Select("select seq_user_id.nextval as rsid from dual")
	int getUserId();
	
	int addUser(User user);
	
	@Select("select count(*) from su_user where status = 1")
	int getAllUserCount();
	
	List<User> findUserList(
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize, 
			@Param("username")String username, @Param("staffId")String staffId);
	
	@Select("select * from su_user where status = 1 and user_id = #{userId}")
	User findObjectById(@Param("userId")Integer userId);
	
	int updateUser(User user);
	
	@Update("update su_user set status = 0 where user_id = #{userId}")
	int deleteUser(@Param("userId")Integer userId);
	
}