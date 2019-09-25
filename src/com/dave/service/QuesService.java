package com.dave.service;

import java.util.List;

import com.dave.common.vo.PageObject;
import com.dave.entity.Ques;
import com.dave.entity.vo.QuesInfo;

/**
 * Ques业务层接口
 * 
 * @author Dave20190826
 *
 */
public interface QuesService {
	
	PageObject<Ques> findQuesList(int pageCurrent, String quesName);
	
	int addQues(QuesInfo quesInfo);
	
	int deleteQues(Integer... quesIds);
	
	QuesInfo getQuesOption(int quesId);
	
	List<String> checkQuesUse(int quesId);
	
	int updateQues(QuesInfo quesInfo);
}
