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
 * Page控制层
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
	 * 登录页面
	 * @return login
	 */
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
		return "login";
	}
	
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
	
	/**
	 * 修改用户密码页面
	 * 
	 * @return system/update_password
	 */
	@RequestMapping("doUpdatePasswordUI")
	public String doUpdatePasswordUI() {
		return "system/update_password";
	}
	
	/**
	 * Hotline使用的调查问卷接口
	 * 
	 * @param model
	 * @param mobile
	 * @param language
	 * @param cli
	 * @param agentId
	 * @param inviteTime
	 * @param paperName
	 * @return
	 */
	@RequestMapping("doSurveyUI")
	public String doSurveyUI(Model model, 
			String mobile, String language, String cli, String agentId, String inviteTime, String paperName) {
		try {
			System.out.println("/survey/doSurveyUI.do?mobile="+mobile+"&language="+language+"&cli="+cli+"&agentId="+agentId+"&inviteTime="+inviteTime+"&paperName="+paperName);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			long userDate = simpleDateFormat.parse(inviteTime).getTime();
			long nowDate = new Date().getTime();
			long dayTimeMillis = 7*24*3600*1000;
			if(dayTimeMillis > nowDate - userDate) {
				int count = surveyService.findIfRepeatAnswer(mobile, paperName);
				if(count == 0) {
					PaperInfo paperInfo = surveyService.findStartPaper(paperName, language);
					if(!(paperInfo == null || StringUtils.isEmpty(paperInfo))) {
						model.addAttribute("language", language);
						model.addAttribute("paperName", paperName);
						model.addAttribute("mobile", mobile);
						model.addAttribute("cli", cli);
						model.addAttribute("agentId", agentId);
						model.addAttribute("inviteTime", inviteTime);
						if("01".equals(paperInfo.getPaperType())) {
							return "survey01";
						}else if("02".equals(paperInfo.getPaperType())) {
							return "survey02";
						}
					}			
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		model.addAttribute("language", language);
		model.addAttribute("chMessage", "抱歉，此連結已經無效！");
		model.addAttribute("engMessage", "Sorry , this link is no longer valid!");
		return "paper404";
	}
	
}