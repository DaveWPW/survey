package com.dave.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dave.entity.vo.PaperInfo;
import com.dave.service.SurveyService;

/**
 * 匿名页面Controller
 * 
 * @author Dave20190823
 * 
 */
@Controller
@RequestMapping("/")
public class PageController {
	@Autowired
    private SurveyService surveyService;
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
		try {
			System.out.println("/survey/doSurveyUI.do?mobile="+mobile+"&language="+language+"&cli="+cli+"&agentId="+agentId+"&startTime="+startTime+"&paperName="+paperName);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			long userDate = simpleDateFormat.parse(startTime).getTime();
			long nowDate = new Date().getTime();
			if(7*24*3600*1000 > nowDate-userDate) {
				PaperInfo paperInfo = surveyService.findStartPaper(paperName, language);
				if(!(paperInfo == null || StringUtils.isEmpty(paperInfo))) {
					if("01".equals(paperInfo.getPaperType())) {
						model.addAttribute("language", language);
						model.addAttribute("paperName", paperName);
						model.addAttribute("mobile", mobile);
						model.addAttribute("cli", cli);
						model.addAttribute("agentId", agentId);
						model.addAttribute("startTime", startTime);
						return "survey01";
					}
				}			
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("chMessage", "抱歉，此連結已經無效！");
		model.addAttribute("engMessage", "Sorry , this link is no longer valid!");
		return "404";	
	}
}