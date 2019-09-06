package com.dave.service;

import com.dave.common.vo.PageObject;
import com.dave.entity.Ques;
import com.dave.entity.vo.QuesInfo;

/**
 * Ques业务层接口
 * 
 * @author Dave2019
 *
 */
public interface QuesService {
	
	PageObject<Ques> findQuesList(int pageCurrent, String quesName);
	
	int addQues(QuesInfo quesInfo);
	
	int selectQuesId();
	
	int deleteQues(Integer... quesIds);
	
	QuesInfo getQuesOption(int quesId);
	
	int updateQues(QuesInfo quesInfo);
}
