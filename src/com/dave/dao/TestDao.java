package com.dave.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

public interface TestDao {
	
	@Select("select * from email_address_list")
	List<Map<String, Object>> findAllData();
	
}
