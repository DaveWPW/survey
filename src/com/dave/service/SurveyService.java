package com.dave.service;

import com.dave.entity.vo.PaperInfo;
import com.dave.entity.vo.ResultInfo;

public interface SurveyService {
	
	PaperInfo findStartPaper(String paperName, String paperLanguage);
	
	int submitSurveyResult(ResultInfo resultInfo);
}
