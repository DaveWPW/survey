package com.dave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.dave.common.vo.Node;
//import com.dave.entity.Menu;

public interface MenuDao {
	
	@Select("select menu_id as id, menu_name as name, parent_id from su_menu where status = 1")
	List<Node> findMenuNodes();
}