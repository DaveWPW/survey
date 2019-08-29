package com.dave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dave.entity.Option;

public interface OptionDao {
	
	int addOption(Option option);
	
	@Delete("delete su_option where ques_id = #{quesId}")
	int deleteOption(@Param("quesId")int quesId);
	
	@Select("select * from su_option where ques_id = #{quesId} order by option_content asc")
	List<Option> selectOptionByQuesId(@Param("quesId")int quesId);
}