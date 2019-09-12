package com.dave.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
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
	
	@Select("select count(*) from su_result")
	int getAllResultCount();
	
	List<Result> findResultList(
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize, @Param("paperName")String paperName);
	
	@Delete("delete from su_result where result_id = #{resultId}")
	int deleteResult(@Param("resultId")int resultId);
	
	@Select("select r.result_id, r.mobile_num, r.cli, r.agent_id, p.paper_name, r.paper_type, r.paper_language, r.start_time, r.end_time from su_result r left join su_paper p on r.paper_id = p.paper_id where r.result_id = #{resultId}")
	Result selectResultById(@Param("resultId")int resultId);
	
}
