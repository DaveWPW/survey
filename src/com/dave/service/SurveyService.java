package com.dave.service;

import com.dave.entity.vo.PaperInfo;

public interface SurveyService {
	
	PaperInfo findStartPaper(String paperName, String paperLanguage);
}
