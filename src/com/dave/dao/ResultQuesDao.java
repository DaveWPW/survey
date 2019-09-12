package com.dave.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.dave.entity.ResultQues;

/**
 * ResultQues持久层接口
 * 
 * @author Dave20190904
 *
 */
public interface ResultQuesDao {
	
	int addResultQues(ResultQues resultQues);
	
	@Delete("delete from su_result_ques where result_id = #{resultId}")
	int deleteResultQues(@Param("resultId")int resultId);
	
}
