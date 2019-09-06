package com.dave.dao;

import org.apache.ibatis.annotations.Select;

import com.dave.entity.Result;

public interface ResultDao {
	
	int addResult(Result result);
	
	@Select("select max(result_id) as result_id from su_result")
	int selectResultId();
}
