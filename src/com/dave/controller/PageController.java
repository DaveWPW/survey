package com.dave.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 匿名页面控制层
 * 
 * @author Dave2019
 * 
 */
@Controller
@RequestMapping("/")
public class PageController {
	/**
	 * 主页
	 * @return index
	 */
	@RequestMapping("doIndexUI")
	public String doIndexUI(){
		return "index";
	}
	/**
	 * 分页部分
	 * @return common/page
	 */
	@RequestMapping("doPageUI")
	public String doPageUI(){
		return "common/page";
	}
	
	@RequestMapping("doSurveyUI")
	public String doSurveyUI(Model model, 
			long mobile, String language, long cli, int agentId, String startTime, String paperName) {
		System.out.println("doSurveyUI.do?mobile="+mobile+"&language="+language+"&cli="+cli+"&agentId="+agentId+"&startTime="+startTime+"&paperName="+paperName);
		String url = "/survey/doSurveyUI.do?mobile="+mobile+"&language="+language+"&cli="+cli+"&agentId="+agentId+"&startTime="+startTime+"&paperName="+paperName;
		//doSurveyUI.do?mobile=88888888888&language=ch&cli=0&agentId=8888&startTime=20190906111111&paperName=test20190902
		model.addAttribute("language", language);
		model.addAttribute("paperName", paperName);
		model.addAttribute("mobile", mobile);
		model.addAttribute("cli", cli);
		model.addAttribute("agentId", agentId);
		model.addAttribute("startTime", startTime);
		model.addAttribute("url", url);
		return "survey";
	}
}