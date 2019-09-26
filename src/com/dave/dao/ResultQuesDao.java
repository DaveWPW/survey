package com.dave.dao;

import java.util.List;

//import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dave.entity.ResultQues;

/**
 * ResultQues持久层接口
 * 
 * @author Dave20190904
 *
 */
public interface ResultQuesDao {
	
	int addResultQues(ResultQues resultQues);
	
//	@Delete("delete from su_result_ques where result_id = #{resultId}")
//	int deleteResultQues(@Param("resultId")int resultId);
	
	@Select("select r.*, q.ques_name from su_result_ques r, su_ques q where r.result_id = #{resultId} and q.ques_id = r.ques_id order by ques_num asc")
	List<ResultQues> selectResultQuesById(@Param("resultId")int resultId);
}
