package com.dave.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dave.common.vo.JsonResult;
import com.dave.entity.vo.PaperInfo;
import com.dave.entity.vo.ResultInfo;
import com.dave.service.SurveyService;

@Controller
@RequestMapping("/survey/")
public class SurveyController {
	@Autowired
    private SurveyService surveyService;
	
	@RequestMapping("doSurveyListUI")
	public String doSurveyListUI() {
		return "system/survey_list";
	}
	
	@RequestMapping("doStartSurvey")
	@ResponseBody
	public JsonResult doStartSurvey(String paperName, String paperLanguage) {
		PaperInfo paperInfo = surveyService.findStartPaper(paperName, paperLanguage);
		if(paperInfo == null) {
			return new JsonResult("没有你想要的问卷！！");
		}
		return new JsonResult(paperInfo);
	}
	
	@RequestMapping("doSubmitSurveyResult")
	@ResponseBody
	public JsonResult doSubmitSurveyResult(ResultInfo resultInfo) {
		int row = surveyService.submitSurveyResult(resultInfo);
		if(row == 1) {
			return new JsonResult("submit succeed!", row);
		}
		return new JsonResult("submit failed!!");
	}
}
