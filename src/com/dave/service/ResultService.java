package com.dave.service;

import java.util.List;

import com.dave.common.vo.PageObject;
import com.dave.entity.Result;
import com.dave.entity.ResultQues;

/**
 * Result业务层接口
 * 
 * @author Dave20190910
 *
 */
public interface ResultService {
	
	PageObject<Result> findResultList(int pageCurrent, String paperName, String startDate, String endDate);
	
	int deleteResult(Integer... resultIds);
	
	List<ResultQues> getResultQues(int resultId);
	
}
