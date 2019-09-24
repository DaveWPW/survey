package com.dave.dao;

import java.util.List;

//import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.dave.entity.Ques;

/**
 * Ques持久层接口
 * 
 * @author Dave20190826
 *
 */
public interface QuesDao {
	
	@Select("select count(*) from su_ques where status=1")
	int getAllQuesCount();
	
	List<Ques> findQuesList(
			@Param("startIndex")int startIndex, @Param("pageSize")int pageSize, @Param("quesName")String quesName);
	
	int addQues(Ques ques);
	
	@Select("select max(ques_id) as ques_id from su_ques")
	int selectQuesId();
	
//	@Delete("delete from su_ques where ques_id = #{quesId}")
	@Update("update su_ques set status = 0 where ques_id = #{quesId}")
	int deleteQues(@Param("quesId")int quesId);
	
	@Select("select * from su_ques where ques_id = #{quesId} and status = 1")
	Ques selectQuesById(@Param("quesId")int quesId);
	
	@Select("select p.paper_name from su_paper_ques pq left join su_paper p on pq.paper_id = p.paper_id where p.status != 0 and pq.ques_id = #{quesId}")
	List<String> checkQuesUse(@Param("quesId")int quesId);
	
	int updateQues(Ques ques);
	
}
