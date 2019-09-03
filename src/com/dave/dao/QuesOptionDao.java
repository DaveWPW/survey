package com.dave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dave.entity.QuesOption;

public interface QuesOptionDao {
	
	int addOption(QuesOption option);
	
	@Delete("delete from su_ques_option where ques_id = #{quesId}")
	int deleteOption(@Param("quesId")int quesId);
	
	@Select("select * from su_ques_option where ques_id = #{quesId} order by option_content asc")
	List<QuesOption> selectOptionByQuesId(@Param("quesId")int quesId);
}