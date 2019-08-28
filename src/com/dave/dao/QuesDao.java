package com.dave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dave.entity.Ques;

/**
 * Ques持久层接口
 * 
 * @author Dave2019
 *
 */
public interface QuesDao {
	
	@Select("select count(*) from su_ques")
	int getAllQuesCount();
	
	List<Ques> findQuesList(
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize, @Param("quesName")String quesName);
	
	int addQues(Ques ques);
	
	@Select("select max(ques_id) as ques_id from su_ques")
	int selectQuesId();
	
	@Delete("delete su_ques where ques_id = #{quesId}")
	int deleteQues(@Param("quesId")int quesId);
	
	int updateQues(Ques ques);
	
	@Select("select * from su_ques where ques_id = #{quesId}")
	Ques selectQuesById(@Param("quesId")int quesId);
	
}
