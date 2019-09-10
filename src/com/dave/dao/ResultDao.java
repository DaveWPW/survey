package com.dave.dao;

import org.apache.ibatis.annotations.Select;

import com.dave.entity.Result;

/**
 * Result持久层接口
 * 
 * @author Dave20190904
 *
 */
public interface ResultDao {
	
	int addResult(Result result);
	
	@Select("select max(result_id) as result_id from su_result")
	int selectResultId();
}
