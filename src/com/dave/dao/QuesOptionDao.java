package com.dave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.dave.entity.QuesOption;

/**
 * QuesOption持久层接口
 * 
 * @author Dave20190827
 *
 */
public interface QuesOptionDao {
	
	int addOption(QuesOption option);
	
	@Delete("delete from su_ques_option where ques_id = #{quesId}")
	int deleteOption(@Param("quesId")int quesId);
	
	@Select("select * from su_ques_option where ques_id = #{quesId} order by option_id asc")
	List<QuesOption> selectOptionByQuesId(@Param("quesId")int quesId);
}