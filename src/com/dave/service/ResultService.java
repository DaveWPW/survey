package com.dave.service;

import com.dave.common.vo.PageObject;
import com.dave.entity.Result;
import com.dave.entity.vo.ResultInfo;

public interface ResultService {
	
	PageObject<Result> findResultList(int pageCurrent, String paperName);
	
	int deleteResult(Integer... resultIds);
	
	ResultInfo getResultQues(int resultId);
	
}
