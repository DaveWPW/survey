package com.dave.service;

import java.util.List;

import com.dave.common.vo.PageObject;
import com.dave.entity.Result;
import com.dave.entity.ResultQues;

public interface ResultService {
	
	PageObject<Result> findResultList(int pageCurrent, String paperName);
	
	int deleteResult(Integer... resultIds);
	
	List<ResultQues> getResultQues(int resultId);
	
}
